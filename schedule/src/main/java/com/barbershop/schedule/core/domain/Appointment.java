package com.barbershop.schedule.core.domain;

import com.barbershop.schedule.core.domain.enums.AppointmentStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
public class Appointment {
    private UUID appointmentId;
    private LocalDate date;
    private LocalTime startAt;
    private int duration;
    private UUID customerId;
    private UUID barberId;
    private List<Integer> serviceIds;
    private AppointmentStatus status;

    public int getDurationBlocks(){
        return duration/15;
    }

    @Builder
    public Appointment(UUID appointmentId, LocalDate date, LocalTime startAt, int duration, UUID customerId,
                       UUID barberId, List<Integer> serviceIds, AppointmentStatus status) {
        this.appointmentId = appointmentId;
        this.date = date;
        this.startAt = startAt;
        this.duration = duration;
        this.customerId = customerId;
        this.barberId = barberId;
        this.serviceIds = serviceIds;
        this.status = status;
    }
}
