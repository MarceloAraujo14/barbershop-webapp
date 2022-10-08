package com.barbershop.schedule.core.usecase.appointment;

import com.barbershop.schedule.core.domain.Appointment;
import com.barbershop.schedule.core.domain.Diary;
import com.barbershop.schedule.core.exception.*;
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

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static com.barbershop.schedule.core.domain.enums.AppointmentStatus.FAILURE;
import static com.barbershop.schedule.core.domain.enums.AppointmentStatus.SCHEDULED;
import static com.barbershop.schedule.core.utils.DomainTestModels.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
    @Captor
    ArgumentCaptor<Diary> diaryCaptor;

    @Test
    void should_schedule_appointment_success() throws ScheduleAppointmentException, OverlapTimeException, InvalidScheduleDateException, ServiceIdNotFoundException {
        Appointment validAppointment = APPOINTMENT_VALID;
        Diary validDiary = VALID_DIARY;

        when(repository.save(any())).thenReturn(any());
        when(getDiaryUseCase.execute(validAppointment.getDate())).thenReturn(validDiary);
        when(updateDiaryUseCase.execute(validDiary)).thenReturn(any());

        scheduleAppointmentUseCase.execute(validAppointment);

        verify(getDiaryUseCase, times(1)).execute(validAppointment.getDate());
        verify(repository, times(1)).save(appointmentCaptor.capture());
        verify(updateDiaryUseCase, times(1)).execute(diaryCaptor.capture());

        verifyNoMoreInteractions(getDiaryUseCase);
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(updateDiaryUseCase);

        assertEquals(SCHEDULED,appointmentCaptor.getValue().getStatus());
        assertEquals(Set.of(9), diaryCaptor.getValue().getBusyTimes());
    }

    @Test
    void should_block_appointment_on_monday(){
        Appointment appointment = APPOINTMENT_INVALID_MONDAY;

        when(repository.save(any())).thenReturn(any());

        assertThrows(InvalidScheduleDateException.class, () -> scheduleAppointmentUseCase.execute(appointment));

        verify(repository, times(1)).save(appointmentCaptor.capture());

        verifyNoInteractions(getDiaryUseCase);
        verifyNoInteractions(updateDiaryUseCase);

        assertEquals(FAILURE, appointmentCaptor.getValue().getStatus());
    }

    @Test
    void should_block_appointment_after_next_weekEnd(){
        Appointment appointment = APPOINTMENT_INVALID_AFTER_LAST_WEEK;

        when(repository.save(any())).thenReturn(any());

        assertThrows(InvalidScheduleDateException.class, () -> scheduleAppointmentUseCase.execute(appointment));

        verify(repository, times(1)).save(appointmentCaptor.capture());

        verifyNoInteractions(getDiaryUseCase);
        verifyNoInteractions(updateDiaryUseCase);

        assertEquals(FAILURE, appointmentCaptor.getValue().getStatus());
    }
    @Test
    void should_block_appointment_before_today(){
        Appointment appointment = APPOINTMENT_INVALID_YESTERDAY;

        when(repository.save(any())).thenReturn(any());

        assertThrows(InvalidScheduleDateException.class, () -> scheduleAppointmentUseCase.execute(appointment));

        verify(repository, times(1)).save(appointmentCaptor.capture());

        verifyNoInteractions(getDiaryUseCase);
        verifyNoInteractions(updateDiaryUseCase);

        assertEquals(FAILURE, appointmentCaptor.getValue().getStatus());
    }

    @Test
    void should_block_appointment_whitout_serviceIds(){
        Appointment appointment = APPOINTMENT_INVALID_WITHOUT_SERVICEID;
        Diary diary = VALID_DIARY;

        when(repository.save(any())).thenReturn(any());
        when(getDiaryUseCase.execute(appointment.getDate())).thenReturn(diary);

        assertThrows(ServiceIdNotFoundException.class, () -> scheduleAppointmentUseCase.execute(appointment));

        verify(repository, times(1)).save(appointmentCaptor.capture());
        verify(getDiaryUseCase, times(1)).execute(appointment.getDate());
        verifyNoInteractions(updateDiaryUseCase);

        assertEquals(FAILURE, appointmentCaptor.getValue().getStatus());
    }

    //todo: create this test after create the Services microsservice
    void should_block_appointment_with_invalid_serviceId(){

    }

    @Test
    void should_block_appointment_overlap_busy_time(){
        Appointment appointment = APPOINTMENT_INVALID_OVERLAP_TIME;
        Diary diary = VALID_DIARY;
        diary.setBusyTimes(Set.of(0,1,2,3,4,5));

        when(repository.save(any())).thenReturn(any());
        when(getDiaryUseCase.execute(appointment.getDate())).thenReturn(diary);
//        when(updateDiaryUseCase.execute(diary)).thenThrow(OverlapTimeException.class);
        assertThrows(OverlapTimeException.class, () -> scheduleAppointmentUseCase.execute(appointment));

        verify(repository, times(1)).save(appointmentCaptor.capture());
        verify(getDiaryUseCase, times(1)).execute(appointment.getDate());
        verifyNoInteractions(updateDiaryUseCase);

        assertEquals(FAILURE, appointmentCaptor.getValue().getStatus());
    }

    @Test
    void should_block_appointment_lunch_time(){
        Appointment appointment = APPOINTMENT_INVALID_LUNCH_TIME;
        Diary diary = VALID_DIARY;

        when(repository.save(any())).thenReturn(any());
        when(getDiaryUseCase.execute(appointment.getDate())).thenReturn(diary);
        doThrow(LunchTimeException.class).when(updateDiaryUseCase).execute(any()); //todo: fix this test
        assertThrows(LunchTimeException.class, () -> scheduleAppointmentUseCase.execute(appointment));

        verify(repository, times(1)).save(appointmentCaptor.capture());
        verify(getDiaryUseCase, times(1)).execute(appointment.getDate());
        verify(updateDiaryUseCase, times(1)).execute(diaryCaptor.capture());

        assertTrue(Objects.nonNull(diaryCaptor.getValue().getBusyTimes()));
        assertEquals(FAILURE, appointmentCaptor.getValue().getStatus());
    }



}
