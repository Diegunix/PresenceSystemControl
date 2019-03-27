package com.presencesystem.controller.dto;

import java.time.LocalDateTime;

import com.presencesystem.commons.enums.ActionEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TimetableDTO {
    
    private long id;
    private UserDTO user;
    private LocalDateTime actionDate;
    private ActionEnum action;

}
