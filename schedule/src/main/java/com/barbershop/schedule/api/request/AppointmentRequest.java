package com.barbershop.schedule.api.request;

import com.barbershop.schedule.api.validation.constraints.ValidDate;
import com.barbershop.schedule.core.domain.Appointment;
import com.barbershop.schedule.core.domain.enums.AppointmentStatus;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;


@NoArgsConstructor
@Data
public class AppointmentRequest {
    @NotBlank
    private String appointmentId;
    @NotBlank(message = "Date is mandatory")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @ValidDate
    private String date;
    @NotNull(message = "Start time is mandatory")
    @DateTimeFormat(pattern = "HH:mm")
    private String startAt;
    @NotNull(message = "duration is mandatory")
    @Min(value = 15, message = "Minumum duration 15 minutes")
    private int duration;
    @NotBlank(message = "Customer Id is mandatory")
    private String customerId;
    @NotBlank(message = "Barber Id is mandatory")
    private String barberId;
    @Valid
    @NotEmpty(message = "Select at least one service")
    private List<Integer> serviceIds;
    private AppointmentStatus status;

    public Appointment toAppointment(){
        return Appointment.builder()
                .appointmentId(UUID.fromString(appointmentId))
                .date(LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .startAt(LocalTime.parse(startAt))
                .duration(duration)
                .customerId(UUID.fromString(customerId))
                .barberId(UUID.fromString(barberId))
                .serviceIds(serviceIds)
                .status(status)
                .build();
    }

    @Builder
    public AppointmentRequest(String date, String startAt, int duration, String customerId,
                              String barberId, List<Integer> serviceIds) {
        this.appointmentId = UUID.randomUUID().toString(); //todo: should this uuid come from frontend?
        this.date = date;
        this.startAt = startAt;
        this.duration = duration;
        this.customerId = customerId;
        this.barberId = barberId;
        this.serviceIds = serviceIds;
        this.status = AppointmentStatus.CREATED;
    }

    @Builder
    public AppointmentRequest(String appointmentId, String date, String startAt, int duration, String customerId,
                              String barberId, List<Integer> serviceIds, AppointmentStatus status) {
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
