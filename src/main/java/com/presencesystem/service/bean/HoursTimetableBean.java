package com.presencesystem.service.bean;

import com.presencesystem.dao.domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class HoursTimetableBean {
    
    private User user;
    private String actionDate;
    private String hours;

}
