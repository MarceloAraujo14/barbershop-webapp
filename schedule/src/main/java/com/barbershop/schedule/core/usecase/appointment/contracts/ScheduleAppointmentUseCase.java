package com.barbershop.schedule.core.usecase.appointment.contracts;

import com.barbershop.schedule.core.domain.Appointment;
import com.barbershop.schedule.core.exception.OverlapTimeException;
import com.barbershop.schedule.core.exception.ScheduleAppointmentException;

public interface ScheduleAppointmentUseCase {
    Appointment execute(Appointment request) throws ScheduleAppointmentException, OverlapTimeException;
}
