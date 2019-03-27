package com.presencesystem.service;

import java.util.List;

import com.presencesystem.controller.dto.QueryFilterDTO;
import com.presencesystem.dao.domain.Timetable;
import com.presencesystem.service.bean.HoursTimetableBean;

public interface TimetableService {

    Timetable signing(String fingerprint);

    List<Timetable> query(Long idUser, QueryFilterDTO filter);
    
    List<HoursTimetableBean> queryHours(Long idUser, QueryFilterDTO filter);

}