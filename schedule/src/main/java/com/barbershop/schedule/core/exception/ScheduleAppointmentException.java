package com.barbershop.schedule.core.exception;

import static com.barbershop.schedule.core.constants.ScheduleErrorMessages.SCHEDULE_APPOINTMENT_FAIULE_MSG;

public class ScheduleAppointmentException extends ScheduleException {

    private static final String TITLE = "Solicitação inválida!";

    public ScheduleAppointmentException() {
        super(TITLE, SCHEDULE_APPOINTMENT_FAIULE_MSG);
    }

    public ScheduleAppointmentException(String message) {
        super(TITLE, message);
    }
}
