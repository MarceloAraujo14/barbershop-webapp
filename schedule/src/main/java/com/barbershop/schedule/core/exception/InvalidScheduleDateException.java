package com.barbershop.schedule.core.exception;

import static com.barbershop.schedule.core.constants.ScheduleErrorMessages.INVALID_DATE_REQUEST_MSG;
import static com.barbershop.schedule.core.constants.ScheduleErrorMessages.INVALID_DATE_TITLE;

public class InvalidScheduleDateException extends ScheduleException {

    public InvalidScheduleDateException() {
        super(INVALID_DATE_TITLE, INVALID_DATE_REQUEST_MSG);
    }
}
