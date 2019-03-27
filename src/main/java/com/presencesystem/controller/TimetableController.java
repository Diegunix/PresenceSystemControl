package com.presencesystem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.presencesystem.controller.dto.HoursTimetableDTO;
import com.presencesystem.controller.dto.QueryFilterDTO;
import com.presencesystem.controller.dto.TimetableDTO;
import com.presencesystem.controller.dto.mappers.HoursTimetableMapper;
import com.presencesystem.controller.dto.mappers.TimetableMapper;
import com.presencesystem.service.TimetableService;

@RestController
public class TimetableController {

    private TimetableService timetableService;

    public TimetableController(TimetableService timetableService) {
        this.timetableService = timetableService;
    }

    @ResponseBody
    @GetMapping(value = "/timetable/signing/{fingerprint}")
    public ResponseEntity<TimetableDTO> signing(@PathVariable("fingerprint") String fingerprint) {
        TimetableDTO result = TimetableMapper.INSTANCE.map(timetableService.signing(fingerprint));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "/user/{idUser}/timetable/query")
    public ResponseEntity<List<TimetableDTO>> query(@PathVariable("idUser") Long idUser, QueryFilterDTO filter) {
        List<TimetableDTO> result = TimetableMapper.INSTANCE.map(timetableService.query(idUser, filter));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "/user/{idUser}/timetable")
    public ResponseEntity<List<HoursTimetableDTO>> queryHours(@PathVariable("idUser") Long idUser, QueryFilterDTO filter) {
        List<HoursTimetableDTO> result = HoursTimetableMapper.INSTANCE.map(timetableService.queryHours(idUser, filter));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}