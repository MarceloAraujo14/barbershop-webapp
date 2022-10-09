package com.barbershop.schedule.core.usecase.appointment;

import com.barbershop.schedule.core.domain.Appointment;
import com.barbershop.schedule.core.domain.Diary;
import com.barbershop.schedule.core.exception.ScheduleException;
import com.barbershop.schedule.core.port.dataprovider.AppointmentRepository;
import com.barbershop.schedule.core.usecase.diary.contracts.GetDiaryUseCase;
import com.barbershop.schedule.core.usecase.diary.contracts.UpdateDiaryUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static com.barbershop.schedule.core.constants.ScheduleErrorMessages.*;
import static com.barbershop.schedule.core.domain.enums.AppointmentStatus.FAILURE;
import static com.barbershop.schedule.core.domain.enums.AppointmentStatus.SCHEDULED;
import static com.barbershop.schedule.core.utils.DomainTestModels.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    Diary diary;

    @BeforeEach
    void setUp() {
        this.diary = VALID_DIARY;
    }

    @AfterEach
    void tearDown() {
        diary.getBusyTimes().clear();
    }

    @Test
    void should_schedule_appointment_success() throws ScheduleException {
        Appointment validAppointment = APPOINTMENT_VALID;

        when(repository.save(any())).thenReturn(any());
        when(getDiaryUseCase.execute(validAppointment.getDate())).thenReturn(diary);
        when(updateDiaryUseCase.execute(diary)).thenReturn(any());

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

        ScheduleException exception = assertThrows(ScheduleException.class, () -> scheduleAppointmentUseCase.execute(appointment));

        verify(repository, times(1)).save(appointmentCaptor.capture());

        verifyNoInteractions(getDiaryUseCase);
        verifyNoInteractions(updateDiaryUseCase);

        assertEquals(FAILURE, appointmentCaptor.getValue().getStatus());
        assertEquals(INVALID_DATE_REQUEST_MSG, exception.getMessage());
    }

    @Test
    void should_block_appointment_after_next_weekEnd(){
        Appointment appointment = APPOINTMENT_INVALID_AFTER_LAST_WEEK;

        when(repository.save(any())).thenReturn(any());

        ScheduleException exception = assertThrows(ScheduleException.class, () -> scheduleAppointmentUseCase.execute(appointment));

        verify(repository, times(1)).save(appointmentCaptor.capture());

        verifyNoInteractions(getDiaryUseCase);
        verifyNoInteractions(updateDiaryUseCase);

        assertEquals(FAILURE, appointmentCaptor.getValue().getStatus());
        assertEquals(INVALID_DATE_REQUEST_MSG, exception.getMessage());
    }
    @Test
    void should_block_appointment_before_today(){
        Appointment appointment = APPOINTMENT_INVALID_YESTERDAY;

        when(repository.save(any())).thenReturn(any());

        ScheduleException exception = assertThrows(ScheduleException.class, () -> scheduleAppointmentUseCase.execute(appointment));

        verify(repository, times(1)).save(appointmentCaptor.capture());

        verifyNoInteractions(getDiaryUseCase);
        verifyNoInteractions(updateDiaryUseCase);

        assertEquals(FAILURE, appointmentCaptor.getValue().getStatus());
        assertEquals(INVALID_DATE_REQUEST_MSG, exception.getMessage());
    }

    @Test
    void should_block_appointment_whitout_serviceIds(){
        Appointment appointment = APPOINTMENT_INVALID_WITHOUT_SERVICEID;

        when(repository.save(any())).thenReturn(any());
        when(getDiaryUseCase.execute(appointment.getDate())).thenReturn(diary);

        ScheduleException exception = assertThrows(ScheduleException.class, () -> scheduleAppointmentUseCase.execute(appointment));

        verify(repository, times(1)).save(appointmentCaptor.capture());
        verify(getDiaryUseCase, times(1)).execute(appointment.getDate());
        verifyNoInteractions(updateDiaryUseCase);

        assertEquals(SERVICE_ID_NOT_FOUND_MSG, exception.getMessage());
        assertEquals(FAILURE, appointmentCaptor.getValue().getStatus());
    }

    //todo: create this test after create the Services microsservice
    void should_block_appointment_with_invalid_serviceId(){

    }

    @Test
    void should_block_appointment_overlap_busy_time(){
        Appointment appointment = APPOINTMENT_INVALID_OVERLAP_TIME;

        diary.getBusyTimes().addAll(Set.of(0,1,2,3,4,5));

        when(repository.save(any())).thenReturn(any());
        when(getDiaryUseCase.execute(appointment.getDate())).thenReturn(diary);

        ScheduleException exception = assertThrows(ScheduleException.class, () -> scheduleAppointmentUseCase.execute(appointment));

        verify(repository, times(1)).save(appointmentCaptor.capture());
        verify(getDiaryUseCase, times(1)).execute(appointment.getDate());
        verifyNoInteractions(updateDiaryUseCase);

        assertEquals(INVALID_OVERLAP_TIME_MSG, exception.getMessage());
        assertEquals(FAILURE, appointmentCaptor.getValue().getStatus());
    }

    @Test
    void should_block_appointment_lunch_time(){
        Appointment appointment = APPOINTMENT_INVALID_LUNCH_TIME;

        when(repository.save(any())).thenReturn(any());
        ScheduleException exception = assertThrows(ScheduleException.class, () -> scheduleAppointmentUseCase.execute(appointment));

        verify(repository, times(1)).save(appointmentCaptor.capture());
        verifyNoInteractions(getDiaryUseCase);
        verifyNoInteractions(updateDiaryUseCase);

        assertEquals(FAILURE, appointmentCaptor.getValue().getStatus());
        assertEquals(INVALID_LUNCH_TIME_MSG, exception.getMessage());
    }

    @Test
    void should_block_appointment_after_business_time(){
        Appointment appointment = APPOINTMENT_AFTER_BUSINESS_TIME;

        when(repository.save(any())).thenReturn(any());
        ScheduleException exception = assertThrows(ScheduleException.class, () -> scheduleAppointmentUseCase.execute(appointment));

        verify(repository, times(1)).save(appointmentCaptor.capture());
        verifyNoInteractions(getDiaryUseCase);
        verifyNoInteractions(updateDiaryUseCase);

        assertEquals(FAILURE, appointmentCaptor.getValue().getStatus());
        assertEquals(INVALID_OUT_BUSINESS_TIME_MSG, exception.getMessage());
    }

    @Test
    void should_block_appointment_before_business_time(){
        Appointment appointment = APPOINTMENT_BEFORE_BUSINESS_TIME;

        when(repository.save(any())).thenReturn(any());
        ScheduleException exception = assertThrows(ScheduleException.class, () -> scheduleAppointmentUseCase.execute(appointment));

        verify(repository, times(1)).save(appointmentCaptor.capture());
        verifyNoInteractions(getDiaryUseCase);
        verifyNoInteractions(updateDiaryUseCase);

        assertEquals(FAILURE, appointmentCaptor.getValue().getStatus());
        assertEquals(INVALID_OUT_BUSINESS_TIME_MSG, exception.getMessage());
    }

}
