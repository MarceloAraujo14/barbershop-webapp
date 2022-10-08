package com.barbershop.schedule.core.exception;

import static com.barbershop.schedule.core.constants.ScheduleErrorMessages.INVALID_TIME_APPOINTMENT_TITLE;
import static com.barbershop.schedule.core.constants.ScheduleErrorMessages.OVERLAP_TIME_APPOINTMENT_MSG;

public class OverlapTimeException extends ScheduleException {

    public OverlapTimeException(String personalizedMessage) {
        super(INVALID_TIME_APPOINTMENT_TITLE, personalizedMessage);
    }

    public OverlapTimeException() {
        super(INVALID_TIME_APPOINTMENT_TITLE, OVERLAP_TIME_APPOINTMENT_MSG);
    }
}
