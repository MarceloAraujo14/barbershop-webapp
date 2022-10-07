package com.barbershop.schedule.core.exception;

import static com.barbershop.schedule.core.constants.ScheduleErrorMessages.OVERLAP_TIME_APPOINTMENT_MSG;

public class OverlapTimeException extends ScheduleException {

    private static final String TITLE = "Solicitação inválida!";

    public OverlapTimeException(String personalizedMessage) {
        super(TITLE, personalizedMessage);
    }

    public OverlapTimeException() {
        super(TITLE, OVERLAP_TIME_APPOINTMENT_MSG);
    }
}
