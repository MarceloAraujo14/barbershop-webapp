package com.barbershop.schedule.core.service;

import com.barbershop.schedule.core.entity.Appointment;
import com.barbershop.schedule.core.exception.ScheduleAppointmentException;
import com.barbershop.schedule.core.usecase.appointment.SaveAppointmentUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static com.barbershop.schedule.core.entity.enums.StatusProcess.PROCESSING;

@Log4j2
@Service
@AllArgsConstructor
public class AppointmentService {

    private final SaveAppointmentUseCase saveAppointmentUseCase;

    public Appointment save(Appointment request) throws ScheduleAppointmentException {
        log.info("m save - request={} - status={}", request, PROCESSING);
        return saveAppointmentUseCase.execute(request);
    }
}
