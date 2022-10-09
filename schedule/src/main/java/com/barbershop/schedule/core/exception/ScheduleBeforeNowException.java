package com.barbershop.schedule.core.exception;

import static com.barbershop.schedule.core.constants.ScheduleErrorMessages.INVALID_TIME_TITLE;
import static com.barbershop.schedule.core.constants.ScheduleErrorMessages.SCHEDULE_BEFORE_NOW_MSG;

public class ScheduleBeforeNowException extends ScheduleException {
    public ScheduleBeforeNowException() {
        super(INVALID_TIME_TITLE, SCHEDULE_BEFORE_NOW_MSG);
    }
}
