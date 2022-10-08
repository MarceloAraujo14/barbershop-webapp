package com.barbershop.schedule.core.exception;

import static com.barbershop.schedule.core.constants.ScheduleErrorMessages.YESTERDAY_SCHEDULE_MSG;

public class ScheduleYesterdayException extends ScheduleException {

    private static final String TITLE = "Solicitação inválida!";
    public ScheduleYesterdayException() {
        super(TITLE, YESTERDAY_SCHEDULE_MSG);
    }
}
