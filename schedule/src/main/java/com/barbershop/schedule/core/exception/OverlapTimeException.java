package com.barbershop.schedule.core.exception;

import static com.barbershop.schedule.core.constants.ScheduleErrorMessages.INVALID_TIME_TITLE;
import static com.barbershop.schedule.core.constants.ScheduleErrorMessages.INVALID_OVERLAP_TIME_MSG;

public class OverlapTimeException extends ScheduleException {

    public OverlapTimeException(String personalizedMessage) {
        super(INVALID_TIME_TITLE, personalizedMessage);
    }

    public OverlapTimeException() {
        super(INVALID_TIME_TITLE, INVALID_OVERLAP_TIME_MSG);
    }
}
