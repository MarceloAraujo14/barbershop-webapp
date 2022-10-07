package com.barbershop.schedule.adapters.spring.data.repository.gateway;

import com.barbershop.schedule.adapters.spring.data.repository.AppointmentJpaRepository;
import com.barbershop.schedule.adapters.spring.data.repository.entity.AppointmentEntity;
import com.barbershop.schedule.core.domain.Appointment;
import com.barbershop.schedule.core.port.dataprovider.AppointmentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static com.barbershop.schedule.adapters.spring.data.repository.entity.mapper.AppointmentMapper.toAppointment;
import static com.barbershop.schedule.adapters.spring.data.repository.entity.mapper.AppointmentMapper.toEntity;
import static com.barbershop.schedule.core.domain.enums.StatusProcess.PROCESSING;
import static com.barbershop.schedule.core.domain.enums.StatusProcess.SUCCESS;

@Log4j2
@AllArgsConstructor
@Service
public class AppointmentRepositoryGateway implements AppointmentRepository {

    private final AppointmentJpaRepository repository;

    @Override
    public Appointment save(Appointment request) {
        log.info("m save - request={} - status={}", request, PROCESSING);
        AppointmentEntity entity = repository.save(toEntity(request));
        log.info("m save - entity={} - status={}", entity, SUCCESS);
        return toAppointment(entity);
    }
}
