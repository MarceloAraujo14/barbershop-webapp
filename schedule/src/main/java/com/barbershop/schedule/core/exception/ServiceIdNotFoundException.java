package com.barbershop.schedule.core.exception;

import static com.barbershop.schedule.core.constants.ScheduleErrorMessages.SERVICE_ID_NOT_FOUND_MSG;
import static com.barbershop.schedule.core.constants.ScheduleErrorMessages.SERVICE_ID_NOT_FOUND_TITLE;

public class ServiceIdNotFoundException extends ScheduleException {
    public ServiceIdNotFoundException() {
        super(SERVICE_ID_NOT_FOUND_TITLE, SERVICE_ID_NOT_FOUND_MSG);
    }
}
