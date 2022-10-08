package com.barbershop.schedule.adapters.spring.repository.entity.mapper;

import com.barbershop.schedule.adapters.spring.repository.entity.AppointmentEntity;
import com.barbershop.schedule.core.domain.Appointment;

public class AppointmentMapper {

    private AppointmentMapper(){}

    public static AppointmentEntity toEntity(Appointment appointment){
        return AppointmentEntity.builder()
                .date(appointment.getDate())
                .startAt(appointment.getStartAt())
                .duration(appointment.getDuration())
                .customerId(appointment.getCustomerId())
                .barberId(appointment.getBarberId())
                .serviceId(appointment.getServiceIds())
                .status(appointment.getStatus())
                .build();
    }

    public static Appointment toAppointment(AppointmentEntity entity){
        return Appointment.builder()
                .appointmentId(entity.getAppointmentId())
                .date(entity.getDate())
                .startAt(entity.getStartAt())
                .duration(entity.getDuration())
                .customerId(entity.getCustomerId())
                .barberId(entity.getBarberId())
                .serviceIds(entity.getServiceId())
                .status(entity.getStatus())
                .build();
    }
}
