package com.barbershop.schedule.adapters.spring.data.repository;

import com.barbershop.schedule.adapters.spring.data.repository.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AppointmentJpaRepository extends JpaRepository<AppointmentEntity, UUID> {
}
