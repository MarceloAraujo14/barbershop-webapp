package com.barbershop.schedule.core.exception;

import static com.barbershop.schedule.core.constants.ScheduleErrorMessages.MONDAY_SCHEDULE_MSG;

public class ScheduleOnMondayException extends ScheduleException {

    private static final String TITLE = "Solicitação inválida!";
    public ScheduleOnMondayException() {
        super(TITLE, MONDAY_SCHEDULE_MSG);
    }
}
