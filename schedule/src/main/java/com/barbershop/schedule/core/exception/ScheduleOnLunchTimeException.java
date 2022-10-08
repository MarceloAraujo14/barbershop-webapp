package com.barbershop.schedule.core.exception;

import static com.barbershop.schedule.core.constants.ScheduleErrorMessages.INVALID_LUNCH_TIME_MSG;
import static com.barbershop.schedule.core.constants.ScheduleErrorMessages.INVALID_TIME_TITLE;

public class ScheduleOnLunchTimeException extends ScheduleException{

    public ScheduleOnLunchTimeException() {
        super(INVALID_TIME_TITLE, INVALID_LUNCH_TIME_MSG);
    }
}
