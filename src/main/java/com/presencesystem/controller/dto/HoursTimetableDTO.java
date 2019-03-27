package com.presencesystem.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class HoursTimetableDTO {

    private UserDTO user;
    private String actionDate;
    private String hours;

}
