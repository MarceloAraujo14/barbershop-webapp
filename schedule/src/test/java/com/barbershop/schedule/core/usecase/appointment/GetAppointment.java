package com.barbershop.schedule.core.usecase.appointment;

import com.barbershop.schedule.core.domain.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static com.barbershop.schedule.core.domain.enums.AppointmentStatus.CREATED;

public class GetAppointment {

    private static final LocalDate WEEK_TUESDAY = LocalDate.of(2020, 10, 11);
    private static final LocalTime VALID_TIME = LocalTime.of(10,0);
    private static final LocalTime LUNCH_TIME = LocalTime.of(12,0);
    private static final LocalTime AFTER_BUSINESS_TIME = LocalTime.of(18,0);
    private static final LocalTime BEFORE_BUSINESS_TIME = LocalTime.of(7,0);


    public static Appointment getValidAppointment(){
        return Appointment.builder()
                .appointmentId(UUID.randomUUID())
                .date(WEEK_TUESDAY)
                .startAt(VALID_TIME)
                .duration(15)
                .customerId(UUID.randomUUID())
                .barberId(UUID.randomUUID())
                .serviceIds(List.of(1))
                .status(CREATED)
                .build();
    }
    public static Appointment getInvalidMondayAppointment(){
        return Appointment.builder()
                .appointmentId(UUID.randomUUID())
                .date(WEEK_TUESDAY.minusDays(1))
                .startAt(VALID_TIME)
                .duration(15)
                .customerId(UUID.randomUUID())
                .barberId(UUID.randomUUID())
                .serviceIds(List.of(1))
                .status(CREATED)
                .build();
    }
    public static Appointment getInvalidYesterdayAppointment(){
        return Appointment.builder()
                .appointmentId(UUID.randomUUID())
                .date(WEEK_TUESDAY.minusDays(1))
                .startAt(LocalTime.of(10,0))
                .duration(15)
                .customerId(UUID.randomUUID())
                .barberId(UUID.randomUUID())
                .serviceIds(List.of(1))
                .status(CREATED)
                .build();
    }
    public static Appointment getInvalidAfterLastWeekAppointment(){
        return Appointment.builder()
                .appointmentId(UUID.randomUUID())
                .date(WEEK_TUESDAY.plusWeeks(1).plusDays(1))
                .startAt(LocalTime.of(10,0))
                .duration(15)
                .customerId(UUID.randomUUID())
                .barberId(UUID.randomUUID())
                .serviceIds(List.of(1))
                .status(CREATED)
                .build();
    }
    public static Appointment getInvalidBeforeNowAppointment(){
        return Appointment.builder()
                .appointmentId(UUID.randomUUID())
                .date(LocalDate.of(2022,10, 11))
                .startAt(LocalTime.of(9,0))
                .duration(15)
                .customerId(UUID.randomUUID())
                .barberId(UUID.randomUUID())
                .serviceIds(List.of(1))
                .status(CREATED)
                .build();
    }
    public static Appointment getInvalidLunchTimeAppointment(){
        return Appointment.builder()
                .appointmentId(UUID.randomUUID())
                .date(LocalDate.of(2022,10, 11))
                .startAt(LocalTime.of(12,0))
                .duration(15)
                .customerId(UUID.randomUUID())
                .barberId(UUID.randomUUID())
                .serviceIds(List.of(1))
                .status(CREATED)
                .build();
    }
}
