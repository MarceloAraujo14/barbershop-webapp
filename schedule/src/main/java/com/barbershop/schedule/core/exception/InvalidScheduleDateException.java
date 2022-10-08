package com.barbershop.schedule.core.exception;

import static com.barbershop.schedule.core.constants.ScheduleErrorMessages.MONDAY_SCHEDULE_MSG;
import static com.barbershop.schedule.core.constants.ScheduleErrorMessages.INVALID_DATE_TITLE;

public class InvalidScheduleDateException extends ScheduleException {

    public InvalidScheduleDateException() {
        super(INVALID_DATE_TITLE, MONDAY_SCHEDULE_MSG);
    }
}
