package com.barbershop.schedule.api.request;

import com.barbershop.schedule.core.entity.Appointment;
import com.barbershop.schedule.core.entity.enums.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentRequest {
    private LocalDate date;
    private LocalTime startAt;
    private int duration;
    private String customerId;
    private String barberId;
    private List<Integer> serviceIds;
    private AppointmentStatus status;

    public Appointment toAppointment(){
        return Appointment.builder()
                .date(date)
                .startAt(startAt)
                .duration(duration)
                .customerId(UUID.fromString(customerId))
                .barberId(UUID.fromString(barberId))
                .serviceIds(serviceIds)
                .status(status)
                .build();
    }

}
