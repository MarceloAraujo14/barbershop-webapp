package com.barbershop.schedule.core.exception;

import static com.barbershop.schedule.core.constants.ScheduleErrorMessages.INVALID_OUT_BUSINESS_TIME_MSG;
import static com.barbershop.schedule.core.constants.ScheduleErrorMessages.INVALID_TIME_TITLE;

public class ScheduleOutBusinessTimeException extends ScheduleException {
    public ScheduleOutBusinessTimeException() {
        super(INVALID_TIME_TITLE, INVALID_OUT_BUSINESS_TIME_MSG);
    }
}
