package com.barbershop.schedule.adapters.spring.data.repository;

import com.barbershop.schedule.adapters.spring.data.repository.entity.DiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DiaryJpaRepository extends JpaRepository<DiaryEntity, LocalDate> {
}
