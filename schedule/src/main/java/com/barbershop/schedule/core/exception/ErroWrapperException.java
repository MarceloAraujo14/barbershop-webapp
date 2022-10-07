package com.barbershop.schedule.core.exception;

import java.util.Map;

public interface ErroWrapperException {
    String getMessage();
    Map<String, Object> getDetails();
}
