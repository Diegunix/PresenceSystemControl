package com.presencesystem.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationEventPublisher;

import com.presencesystem.controller.dto.QueryFilterDTO;
import com.presencesystem.dao.domain.Timetable;
import com.presencesystem.dao.domain.User;
import com.presencesystem.dao.repository.TimetableRepository;
import com.presencesystem.service.bean.HoursTimetableBean;
import com.presencesystem.service.impl.TimetableServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TimetableServiceTest {

    TimetableService service;

    private TimetableRepository timetableRepository;
    private UserService userService;
    private ApplicationEventPublisher applicationEventPublisher;

    @Before
    public void prepareEnvironment() {
        timetableRepository = mock(TimetableRepository.class);
        applicationEventPublisher = mock(ApplicationEventPublisher.class);
        userService = mock(UserService.class);
        service = new TimetableServiceImpl(timetableRepository, userService, applicationEventPublisher);
    }

    @Test
    public void testSigning() {

        User user = new User();
        user.setId(2L);
        user.setFingerprint("123456789");
        Timetable time = new Timetable();
        time.setId(40L);
        time.setUser(user);
        time.setAction(true);

        when(userService.findByFingerprint(any(String.class))).thenReturn(user);

        when(timetableRepository.save(any(Timetable.class))).thenReturn(time);

        when(timetableRepository.findByUserToday(any(User.class))).thenReturn(Optional.ofNullable(time));

        Timetable resultData = service.signing("Key SHA-512");
        verify(applicationEventPublisher).publishEvent(any());
        assertThat(resultData.getAction(), is(Boolean.FALSE));
    }

    @Test
    public void testQuery() {
        List<Timetable> founds = new ArrayList<>();
        Timetable time = new Timetable();
        time.setId(2L);
        founds.add(time);

        User user = new User();
        user.setId(2L);
        user.setFingerprint("123456789");
        user.setFirstName("Name");
        user.setLastName("lastName");
        user.setEmail("mail");

        when(userService.findOne(anyLong())).thenReturn(user);

        when(timetableRepository.findByUserByPeriod(any(User.class), any(QueryFilterDTO.class))).thenReturn(founds);

        QueryFilterDTO filter = new QueryFilterDTO();
        List<Timetable> resultData = service.query(1L, filter);
        assertThat(resultData.size(), is(1));
    }

    @Test
    public void testQueryHours() {
        List<Object[]> founds = new ArrayList<>();
        Object[] obj = { "data1", "data2" };
        founds.add(obj);

        List<Timetable> result = new ArrayList<>();
        Timetable time = new Timetable();
        time.setId(2L);
        result.add(time);

        User user = new User();
        user.setId(2L);
        user.setFingerprint("123456789");
        user.setFirstName("Name");
        user.setLastName("lastName");
        user.setEmail("mail");

        when(userService.findOne(anyLong())).thenReturn(user);

        when(timetableRepository.findHoursByUserByPeriod(any(User.class), any(QueryFilterDTO.class))).thenReturn(founds);

        QueryFilterDTO filter = new QueryFilterDTO();
        List<HoursTimetableBean> resultData = service.queryHours(1L, filter);
        assertThat(resultData.size(), is(1));
    }

}
