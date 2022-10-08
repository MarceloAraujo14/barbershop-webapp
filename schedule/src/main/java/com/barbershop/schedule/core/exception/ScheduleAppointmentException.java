package com.barbershop.schedule.core.exception;

import static com.barbershop.schedule.core.constants.ScheduleErrorMessages.INVALID_DATE_TITLE;
import static com.barbershop.schedule.core.constants.ScheduleErrorMessages.SCHEDULE_APPOINTMENT_FAIULE_MSG;

public class ScheduleAppointmentException extends ScheduleException {

    public ScheduleAppointmentException() {
        super(INVALID_DATE_TITLE, SCHEDULE_APPOINTMENT_FAIULE_MSG);
    }

    public ScheduleAppointmentException(String message) {
        super(INVALID_DATE_TITLE, message);
    }
}
