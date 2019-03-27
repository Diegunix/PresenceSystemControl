package com.presencesystem.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.presencesystem.controller.dto.QueryFilterDTO;
import com.presencesystem.dao.domain.Timetable;
import com.presencesystem.dao.domain.User;

public interface TimetableRepository extends JpaRepository<Timetable, Long> {

    @Query(value = "select * from timetable \n" + "where id_user = :#{#user.id} and\n"
            + "DATE_FORMAT(action_date, \"%Y-%m-%d\") = CURDATE() and\n" + "action_date = (select max(action_date) from timetable \n"
            + "               where id_user = :#{#user.id} and\n"
            + "               DATE_FORMAT(action_date, \"%Y-%m-%d\") = CURDATE())", nativeQuery = true)
    Optional<Timetable> findByUserToday(@Param("user") User user);

    @Query(value = "select * from timetable \n" + "where id_user = :#{#user.id} and\n"
            + "(action_date > :#{#filter.startDate} or :#{#filter.startDate} is null) and\n"
            + "(action_date < :#{#filter.endDate} or :#{#filter.endDate} is null)", nativeQuery = true)
    List<Timetable> findByUserByPeriod(@Param("user") User user, @Param("filter") QueryFilterDTO filter);

    @Query(value = "select  TablaA.fecha, CONVERT(SEC_TO_TIME(TablaB.salida- TablaA.entrada),char) diferencia from \n"
            + "    (select DATE_FORMAT(action_date, \"%Y-%m-%d\") fecha, sum(UNIX_TIMESTAMP(action_date)) entrada\n"
            + "     from timetable\n" + "     where `action` = 1\n" + "     and id_user = :#{#user.id} and\n"
            + "     (action_date > :#{#filter.startDate} or :#{#filter.startDate} is null) and\n"
            + "     (action_date < :#{#filter.endDate} or :#{#filter.endDate} is null) and\n"
            + "     DATE_FORMAT(action_date, \"%Y-%m-%d\") in (select distinct DATE_FORMAT(action_date, \"%Y-%m-%d\") from timetable where id_user = :#{#user.id})\n"
            + "     group by DATE_FORMAT(action_date, \"%Y-%m-%d\")) TablaA left join \n"
            + "    (select DATE_FORMAT(action_date, \"%Y-%m-%d\")fecha, sum(UNIX_TIMESTAMP(action_date)) salida\n" + "     from timetable\n"
            + "     where `action` = 0\n" + "     and id_user = :#{#user.id} and\n"
            + "     (action_date > :#{#filter.startDate} or :#{#filter.startDate} is null) and\n"
            + "     (action_date < :#{#filter.endDate} or :#{#filter.endDate} is null) and\n"
            + "     DATE_FORMAT(action_date, \"%Y-%m-%d\") in (select distinct DATE_FORMAT(action_date, \"%Y-%m-%d\") from timetable where id_user = :#{#user.id})\n"
            + "     group by DATE_FORMAT(action_date, \"%Y-%m-%d\")) TablaB\n"
            + "     on TablaA.fecha = TablaB.fecha\n", nativeQuery = true)
    List<Object[]> findHoursByUserByPeriod(@Param("user") User user, @Param("filter") QueryFilterDTO filter);

}