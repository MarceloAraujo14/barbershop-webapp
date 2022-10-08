package com.barbershop.schedule.core.exception;

import static com.barbershop.schedule.core.constants.ScheduleErrorMessages.INVALID_TIME_TITLE;
import static com.barbershop.schedule.core.constants.ScheduleErrorMessages.INVALID_OVERLAP_TIME_MSG;

public class ScheduleOverlapTimeException extends ScheduleException {

    public ScheduleOverlapTimeException(String personalizedMessage) {
        super(INVALID_TIME_TITLE, personalizedMessage);
    }

    public ScheduleOverlapTimeException() {
        super(INVALID_TIME_TITLE, INVALID_OVERLAP_TIME_MSG);
    }
}
