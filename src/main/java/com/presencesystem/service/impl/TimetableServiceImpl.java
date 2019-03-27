package com.presencesystem.service.impl;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.presencesystem.controller.dto.QueryFilterDTO;
import com.presencesystem.dao.domain.Timetable;
import com.presencesystem.dao.domain.User;
import com.presencesystem.dao.repository.TimetableRepository;
import com.presencesystem.service.TimetableService;
import com.presencesystem.service.UserService;
import com.presencesystem.service.bean.HoursTimetableBean;
import com.presencesystem.service.event.TimeEvent;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TimetableServiceImpl implements TimetableService {

    /**
     * Here we will define the maximum hours per day for users.
     */
    private static final int HOURS_DAY = 10;
    private TimetableRepository timetableRepository;
    private UserService userService;
    private ApplicationEventPublisher applicationEventPublisher;

    public TimetableServiceImpl(TimetableRepository timetableRepository, UserService userService,
            ApplicationEventPublisher applicationEventPublisher) {
        this.userService = userService;
        this.timetableRepository = timetableRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public Timetable signing(String fingerprint) {
        User user = userService.findByFingerprint(fingerprint);
        Timetable time = Timetable.builder().user(user).actionDate(LocalDateTime.now(Clock.systemUTC())).action(getAction(user)).build();
        timetableRepository.save(time);

        TimeEvent event = new TimeEvent(time);
        applicationEventPublisher.publishEvent(event);

        return time;
    }

    private Boolean getAction(User user) {
        Optional<Timetable> lastTime = getLastSigninToday(user);
        if (lastTime.isPresent()) {
            return !lastTime.get().getAction();
        }
        return true;
    }

    private Optional<Timetable> getLastSigninToday(User user) {
        return timetableRepository.findByUserToday(user);
    }

    public List<Timetable> query(Long idUser, QueryFilterDTO filter) {
        User user = userService.findOne(idUser);
        return timetableRepository.findByUserByPeriod(user, filter);
    }

    public List<HoursTimetableBean> queryHours(Long idUser, QueryFilterDTO filter) {
        User user = userService.findOne(idUser);
        List<Object[]> objects = timetableRepository.findHoursByUserByPeriod(user, filter);
        return mapContentToList(objects, user);
    }

    private List<HoursTimetableBean> mapContentToList(List<Object[]> objects, User user) {
        return objects.stream().map(object -> {
            HoursTimetableBean hours = HoursTimetableBean.builder().user(user).build();
            hours.setActionDate(((String) object[0]));
            if (object[1] != null) {
                hours.setHours(((String) object[1]));
            }
            return hours;
        }).collect(Collectors.toList());
    }

    @Async
    @EventListener
    public void calculateAnomaly(TimeEvent event) {
        Timetable time = event.getSource();
        try {
            List<HoursTimetableBean> hoursUserTimetable = queryHours(time.getUser().getId(),
                    QueryFilterDTO.builder().startDate(ZonedDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0))
                            .endDate(ZonedDateTime.now().withHour(0).withMinute(0)).build());

            if (validateHours(hoursUserTimetable)) {
                log.info("Enviar datos al servicio de notificaciones para notificar anomalia.");
            }
        } catch (Exception e) {
            log.error(String.format("Error on calculateAnomaly for UserId %s ", time.getUser().getId()), e);
        }
    }

    private boolean validateHours(List<HoursTimetableBean> hoursUserTimetable) {
        for (HoursTimetableBean hour : hoursUserTimetable) {
            try {
                LocalTime date = LocalTime.parse(hour.getHours());
                if (date.getHour() > HOURS_DAY) {
                    return true;
                }
            } catch (DateTimeParseException e) {
                return true;
            }
        }
        return false;
    }

}