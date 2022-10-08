package com.barbershop.schedule.core.utils;

import com.barbershop.schedule.core.domain.Appointment;
import com.barbershop.schedule.core.domain.Diary;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static com.barbershop.schedule.core.domain.enums.AppointmentStatus.CREATED;

public class DomainTestModels {

    public static final LocalDate WEEK_TUESDAY = new Diary(LocalDate.now()).getWeekTuesday();
    public static final LocalTime VALID_TIME = LocalTime.of(10,0);
    public static final LocalTime LUNCH_TIME = LocalTime.of(12,0);
    public static final LocalTime AFTER_BUSINESS_TIME = LocalTime.of(18,0);
    public static final LocalTime BEFORE_BUSINESS_TIME = LocalTime.of(7,0);

    public static Diary VALID_DIARY= Diary.builder()
                .date(WEEK_TUESDAY)
                .build();


    public static final Appointment APPOINTMENT_VALID = Appointment.builder()
                .appointmentId(UUID.randomUUID())
                .date(WEEK_TUESDAY.plusWeeks(1))
                .startAt(VALID_TIME)
                .duration(15)
                .customerId(UUID.randomUUID())
                .barberId(UUID.randomUUID())
                .serviceIds(List.of(1))
                .status(CREATED)
                .build();

    public static Appointment APPOINTMENT_INVALID_MONDAY = Appointment.builder()
                .appointmentId(UUID.randomUUID())
                .date(WEEK_TUESDAY.minusDays(1))
                .startAt(VALID_TIME)
                .duration(15)
                .customerId(UUID.randomUUID())
                .barberId(UUID.randomUUID())
                .serviceIds(List.of(1))
                .status(CREATED)
                .build();

    public static Appointment APPOINTMENT_INVALID_YESTERDAY =  Appointment.builder()
                .appointmentId(UUID.randomUUID())
                .date(LocalDate.now().minusDays(1))
                .startAt(LocalTime.of(10,0))
                .duration(15)
                .customerId(UUID.randomUUID())
                .barberId(UUID.randomUUID())
                .serviceIds(List.of(1))
                .status(CREATED)
                .build();

    public static Appointment APPOINTMENT_INVALID_AFTER_LAST_WEEK = Appointment.builder()
                .appointmentId(UUID.randomUUID())
                .date(WEEK_TUESDAY.plusWeeks(1).plusDays(1))
                .startAt(LocalTime.of(10,0))
                .duration(15)
                .customerId(UUID.randomUUID())
                .barberId(UUID.randomUUID())
                .serviceIds(List.of(1))
                .status(CREATED)
                .build();

    public static Appointment APPOINTMENT_INVALID_WITHOUT_SERVICEID= Appointment.builder()
            .appointmentId(UUID.randomUUID())
            .date(WEEK_TUESDAY.plusWeeks(1).plusDays(1))
            .startAt(LocalTime.of(10,0))
            .duration(15)
            .serviceIds(List.of())
            .customerId(UUID.randomUUID())
            .barberId(UUID.randomUUID())
            .status(CREATED)
            .build();

    public static Appointment APPOINTMENT_INVALID_BEFORE_NOW=Appointment.builder()
                .appointmentId(UUID.randomUUID())
                .date(LocalDate.of(2022,10, 11))
                .startAt(LocalTime.now().minusHours(1))
                .duration(15)
                .customerId(UUID.randomUUID())
                .barberId(UUID.randomUUID())
                .serviceIds(List.of(1))
                .status(CREATED)
                .build();

    public static Appointment APPOINTMENT_INVALID_OVERLAP_TIME=Appointment.builder()
            .appointmentId(UUID.randomUUID())
            .date(WEEK_TUESDAY.plusWeeks(1))
            .startAt(LocalTime.of(8,0))
            .duration(15)
            .customerId(UUID.randomUUID())
            .barberId(UUID.randomUUID())
            .serviceIds(List.of(1))
            .status(CREATED)
            .build();

    public static Appointment APPOINTMENT_INVALID_LUNCH_TIME= Appointment.builder()
                .appointmentId(UUID.randomUUID())
                .date(LocalDate.of(2022,10, 11))
                .startAt(LocalTime.of(12,0))
                .duration(15)
                .customerId(UUID.randomUUID())
                .barberId(UUID.randomUUID())
                .serviceIds(List.of(1))
                .status(CREATED)
                .build();

    public static Appointment APPOINTMENT_AFTER_BUSINESS_TIME= Appointment.builder()
            .appointmentId(UUID.randomUUID())
            .date(LocalDate.of(2022,10, 11))
            .startAt(LocalTime.of(18,0))
            .duration(15)
            .customerId(UUID.randomUUID())
            .barberId(UUID.randomUUID())
            .serviceIds(List.of(1))
            .status(CREATED)
            .build();

    public static Appointment APPOINTMENT_BEFORE_BUSINESS_TIME= Appointment.builder()
            .appointmentId(UUID.randomUUID())
            .date(LocalDate.of(2022,10, 11))
            .startAt(LocalTime.of(18,0))
            .duration(15)
            .customerId(UUID.randomUUID())
            .barberId(UUID.randomUUID())
            .serviceIds(List.of(1))
            .status(CREATED)
            .build();
}
