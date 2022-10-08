package com.barbershop.schedule.core.usecase.appointment.contracts;

import com.barbershop.schedule.core.domain.Appointment;
import com.barbershop.schedule.core.exception.InvalidScheduleDateException;
import com.barbershop.schedule.core.exception.OverlapTimeException;
import com.barbershop.schedule.core.exception.ScheduleAppointmentException;
import com.barbershop.schedule.core.exception.ServiceIdNotFoundException;

public interface ScheduleAppointmentUseCase {
    Appointment execute(Appointment request) throws ScheduleAppointmentException, OverlapTimeException, InvalidScheduleDateException, ServiceIdNotFoundException;
}
