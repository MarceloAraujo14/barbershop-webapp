package com.barbershop.schedule.api.request;

import com.barbershop.schedule.api.validation.constraints.ValidDate;
import com.barbershop.schedule.core.entity.Appointment;
import com.barbershop.schedule.core.entity.enums.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentRequest {

    @NotBlank(message = "Date is mandatory")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @ValidDate
    private String date;
    @NotNull(message = "Start time is mandatory")
    @DateTimeFormat(pattern = "HH:mm")
    private String startAt;
    @NotNull(message = "duration is mandatory")
    private int duration;
    @NotBlank(message = "Customer Id is mandatory")
    private String customerId;
    @NotBlank(message = "Barber Id is mandatory")
    private String barberId;

    @Valid
    @NotEmpty(message = "Must select at least one service")
    private List<Integer> serviceIds;
    private AppointmentStatus status;

    public Appointment toAppointment(){
        return Appointment.builder()
                .date(LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .startAt(LocalTime.parse(startAt))
                .duration(duration)
                .customerId(UUID.fromString(customerId))
                .barberId(UUID.fromString(barberId))
                .serviceIds(serviceIds)
                .status(status)
                .build();
    }
}
