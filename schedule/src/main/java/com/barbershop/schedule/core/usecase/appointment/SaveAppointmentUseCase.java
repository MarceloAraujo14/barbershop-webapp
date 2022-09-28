package com.barbershop.schedule.core.usecase.appointment;

import com.barbershop.schedule.core.entity.Appointment;
import com.barbershop.schedule.core.port.repository.AppointmentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static com.barbershop.schedule.core.entity.enums.StatusProcess.PROCESSING;

@Log4j2
@Service
@AllArgsConstructor
public class SaveAppointmentUseCase {

    private final AppointmentRepository repository;

    public Appointment execute(Appointment request){
        log.info("m save - request={} - status={}", request, PROCESSING);
        return repository.save(request);
    }
}
