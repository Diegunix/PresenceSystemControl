package com.presencesystem.service.event;

import org.springframework.context.ApplicationEvent;

import com.presencesystem.dao.domain.Timetable;

public class TimeEvent extends ApplicationEvent {
    
    private static final long serialVersionUID = 1090179429914468342L;
    
    private String message;
 
    public TimeEvent(Timetable source) {
        super(source);
    }
    public String getMessage() {
        return message;
    }

    @Override
    public Timetable getSource() {
        return (Timetable) super.getSource();
    }
}