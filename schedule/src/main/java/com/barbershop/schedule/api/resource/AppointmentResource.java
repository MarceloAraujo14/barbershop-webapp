package com.barbershop.schedule.api.resource;

import com.barbershop.schedule.api.request.AppointmentRequest;
import com.barbershop.schedule.api.response.AppointmentResponse;
import com.barbershop.schedule.core.domain.Appointment;
import com.barbershop.schedule.core.domain.enums.AppointmentStatus;
import com.barbershop.schedule.core.exception.ScheduleAppointmentException;
import com.barbershop.schedule.core.usecase.appointment.contracts.ScheduleAppointmentUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.barbershop.schedule.core.domain.enums.StatusProcess.NEW;
import static com.barbershop.schedule.core.domain.enums.StatusProcess.SUCCESS;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/appointment")
public class AppointmentResource {

    private final ScheduleAppointmentUseCase scheduleAppointmentUseCase;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public AppointmentResponse create(@Valid @RequestBody AppointmentRequest request) throws ScheduleAppointmentException {

        request.setStatus(AppointmentStatus.CREATED);
        log.info("m create - request={} - status={}", request, NEW);
        Appointment appointment = scheduleAppointmentUseCase.execute(request.toAppointment());

        log.info("m create - appointment={} - status={}", appointment, SUCCESS);
        return AppointmentResponse.toResponse(appointment);
    }
}
