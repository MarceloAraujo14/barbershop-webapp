package com.barbershop.schedule.core.usecase.appointment;

import com.barbershop.schedule.core.port.dataprovider.AppointmentRepository;
import com.barbershop.schedule.core.usecase.diary.contracts.GetDiaryUseCase;
import com.barbershop.schedule.core.usecase.diary.contracts.UpdateDiaryUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @Test
    void should_schedule_appointment_success(){

    }


}
