package com.barbershop.schedule.adapters.spring.repository.gateway;

import com.barbershop.schedule.adapters.spring.repository.AppointmentJpaRepository;
import com.barbershop.schedule.adapters.spring.repository.entity.AppointmentEntity;
import com.barbershop.schedule.adapters.spring.repository.entity.mapper.AppointmentMapper;
import com.barbershop.schedule.core.domain.Appointment;
import com.barbershop.schedule.core.port.dataprovider.AppointmentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

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
        AppointmentEntity entity = repository.save(AppointmentMapper.toEntity(request));
        log.info("m save - entity={} - status={}", entity, SUCCESS);
        return AppointmentMapper.toAppointment(entity);
    }
}
