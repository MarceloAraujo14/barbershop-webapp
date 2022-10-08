package com.barbershop.schedule.core.usecase.appointment;

import com.barbershop.schedule.core.domain.Appointment;
import com.barbershop.schedule.core.port.dataprovider.AppointmentRepository;
import com.barbershop.schedule.core.usecase.diary.contracts.GetDiaryUseCase;
import com.barbershop.schedule.core.usecase.diary.contracts.UpdateDiaryUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static com.barbershop.schedule.core.domain.enums.AppointmentStatus.CREATED;

@ExtendWith(MockitoExtension.class)
class ScheduleAppointmentUseCaseTest {

    @InjectMocks
    private ScheduleAppointmentUseCaseImpl scheduleAppointmentUseCase;
    @Mock
    private AppointmentRepository repository;
    @Mock
    private GetDiaryUseCase getDiaryUseCase;
    @Mock
    private UpdateDiaryUseCase updateDiaryUseCase;
    @Captor
    ArgumentCaptor<Appointment> appointmentCaptor;

    @Test
    void should_schedule_appointment_success(){

    }


}
