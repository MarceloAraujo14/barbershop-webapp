package com.barbershop.schedule.adapters.spring.data.repository.entity;

import com.barbershop.schedule.core.entity.Appointment;
import com.barbershop.schedule.core.entity.enums.AppointmentStatus;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
@Builder
@Entity(name = "appointment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AppointmentEntity {
    @Id
    @Column(name = "id_appointment", updatable = false, unique = true, nullable = false, length = 16)
    @GeneratedValue
    private UUID appointmentId;
    @Column(name = "date", nullable = false)
    private LocalDate date;
    @Column(name = "start_at", nullable = false)
    private LocalTime startAt;
    @Column(name = "duration", nullable = false)
    private int duration;
    @Column(name = "customer_id", nullable = false)
    private UUID customerId;
    @Column(name = "barber_id", nullable = false)
    private UUID barberId;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AppointmentStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AppointmentEntity that = (AppointmentEntity) o;
        return appointmentId != null && Objects.equals(appointmentId, that.appointmentId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
