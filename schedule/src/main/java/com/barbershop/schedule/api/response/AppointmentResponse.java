package com.barbershop.schedule.api.response;

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
public class AppointmentResponse {
    private UUID appointmentId;
    private LocalDate date;
    private LocalTime startAt;
    private int duration;
    private UUID customerId;
    private UUID barberId;
    private List<Integer> serviceIds;
    private AppointmentStatus status;

    public static AppointmentResponse toResponse(Appointment appointment){
        return AppointmentResponse.builder()
                .appointmentId(appointment.getAppointmentId())
                .date(appointment.getDate())
                .startAt(appointment.getStartAt())
                .duration(appointment.getDuration())
                .customerId(appointment.getCustomerId())
                .barberId(appointment.getBarberId())
                .serviceIds(appointment.getServiceIds())
                .status(appointment.getStatus())
                .build();
    }
}
