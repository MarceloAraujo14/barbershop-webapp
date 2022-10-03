package com.barbershop.schedule.core.entity;

import com.barbershop.schedule.core.entity.enums.AppointmentStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
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
}
