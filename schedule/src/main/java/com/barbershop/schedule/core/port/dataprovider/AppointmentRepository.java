package com.barbershop.schedule.core.port.dataprovider;

import com.barbershop.schedule.core.domain.Appointment;

public interface AppointmentRepository {
    Appointment save(Appointment appointment);
}
