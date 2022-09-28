package com.barbershop.schedule.core.port.repository;

import com.barbershop.schedule.core.entity.Appointment;

public interface AppointmentRepository {
    Appointment save(Appointment appointment);
}
