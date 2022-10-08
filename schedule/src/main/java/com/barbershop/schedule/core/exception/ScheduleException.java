package com.barbershop.schedule.core.exception;

import java.util.Map;

public class ScheduleException extends Exception implements ErroWrapperException{
    protected final String title;
    protected final String message;

    public ScheduleException(String title, String message) {
        this.title = title;
        this.message = message;
    }

    @Override
    public Map<String, Object> getDetails() {
        return Map.of(title, message);
    }

    @Override
    public String getMessage(){
        return message;
    }

    public String getTitle(){
        return title;
    }
}
