package com.presencesystem.controller.dto;

import java.time.ZonedDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QueryFilterDTO {

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private ZonedDateTime startDate;
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private ZonedDateTime endDate;

}
