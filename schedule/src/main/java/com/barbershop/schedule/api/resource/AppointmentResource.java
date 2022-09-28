package com.barbershop.schedule.api.resource;

import com.barbershop.schedule.api.response.AppointmentResponse;
import com.barbershop.schedule.core.entity.Appointment;
import com.barbershop.schedule.core.entity.enums.AppointmentStatus;
import com.barbershop.schedule.core.service.AppointmentService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.barbershop.schedule.core.entity.enums.StatusProcess.NEW;
import static com.barbershop.schedule.core.entity.enums.StatusProcess.SUCCESS;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/appointment")
public class AppointmentResource {

    private final AppointmentService appointmentService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public AppointmentResponse create(Appointment request){

        request.setCustomerId(UUID.randomUUID());
        request.setBarberId(UUID.randomUUID());
        request.setStatus(AppointmentStatus.CREATED);

        log.info("m create - request={} - status={}", request, NEW);

        Appointment appointment = appointmentService.save(request);

        log.info("m create - appointment={} - status={}", appointment, SUCCESS);
        return AppointmentResponse.toResponse(appointment);
    }
}
