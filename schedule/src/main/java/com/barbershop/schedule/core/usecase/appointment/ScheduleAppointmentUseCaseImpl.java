package com.barbershop.schedule.core.usecase.appointment;

import com.barbershop.schedule.core.domain.Appointment;
import com.barbershop.schedule.core.domain.Diary;
import com.barbershop.schedule.core.domain.enums.AppointmentStatus;
import com.barbershop.schedule.core.exception.*;
import com.barbershop.schedule.core.port.dataprovider.AppointmentRepository;
import com.barbershop.schedule.core.usecase.appointment.contracts.ScheduleAppointmentUseCase;
import com.barbershop.schedule.core.usecase.diary.contracts.GetDiaryUseCase;
import com.barbershop.schedule.core.usecase.diary.contracts.UpdateDiaryUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.barbershop.schedule.core.domain.enums.StatusProcess.FAILURE;
import static com.barbershop.schedule.core.domain.enums.StatusProcess.PROCESSING;

@Log4j2
@Service
@AllArgsConstructor
public class ScheduleAppointmentUseCaseImpl implements ScheduleAppointmentUseCase {

    private final AppointmentRepository repository;
    private final GetDiaryUseCase getDiaryUseCase;
    private final UpdateDiaryUseCase updateDiaryUseCase;
    //    private final ServiceClient serviceClient;

    @Transactional
    @Override
    public Appointment execute(Appointment request) throws ScheduleException {
        log.info("m execute - request={} - status={}", request, PROCESSING);
        try {
            validDateAppointment(request.getDate());
            validateBeforeNowAppointment(request.getStartAt());
            validateLunchTimeAppointment(request);
            validateBusinessTimeRequest(request);
            Diary diary = getDiaryUseCase.execute(request.getDate());
            validateOverlapBusyTime(diary, request);
            validateRequestServices(request.getServiceIds(), request.getAppointmentId());
            request.setStatus(AppointmentStatus.SCHEDULED);
            Appointment appointment = repository.save(request);
            setBusyTime(diary, request);
            return appointment;
        }catch (ScheduleException e){
            log.info("m execute - request={} - exception={} - status={} ", request, e.getMessage(), FAILURE);
            request.setStatus(AppointmentStatus.FAILURE);
            repository.save(request);
            throw new ScheduleException(e.getTitle(), e.getMessage());
        }
    }

    private void validDateAppointment(LocalDate requestDate) throws InvalidScheduleDateException {
        LocalDate thisTuesday = Diary.builder().date(LocalDate.now()).build().getWeekTuesday();
        LocalDate nextWeekEnd = thisTuesday.plusWeeks(1).plusDays(5);
        LocalDate today = LocalDate.now();
        LocalDate monday = thisTuesday.minusDays(1);

        if (monday.isEqual(requestDate)
            || today.isAfter(requestDate)
            || nextWeekEnd.isBefore(requestDate)
        ){
            throw new InvalidScheduleDateException();
        }
    }

    private void validateBeforeNowAppointment(LocalTime startAt) throws ScheduleBeforeNowException {
        if (LocalTime.now().isAfter(startAt)){
            throw new ScheduleBeforeNowException();
        }
    }

    private void validateLunchTimeAppointment(Appointment request) throws ScheduleOnLunchTimeException {
        if(request.getStartAt().isAfter(LocalTime.of(11,59))
        && request.getStartAt().plusMinutes(request.getDuration()).isBefore(LocalTime.of(13,0))){
            log.info("m validateLunchTimeAppointment - request={} - status={}", request, FAILURE);
            throw new ScheduleOnLunchTimeException();
        }
    }

    private void validateBusinessTimeRequest(Appointment request) throws ScheduleOutBusinessTimeException {
        if(request.getStartAt().plusMinutes(request.getDuration()).isAfter(LocalTime.of(17,45))
                || request.getStartAt().isBefore(LocalTime.of(8,0))){
            log.info("m validateLunchTimeAppointment - request={} - status={}", request, FAILURE);
            throw new ScheduleOutBusinessTimeException();
        }
    }

    private void validateOverlapBusyTime(Diary diary, Appointment request) throws ScheduleOverlapTimeException {
        int startBlock = diary.getStartBlock(request.getStartAt());
        int durationBlocks = request.getDurationBlocks();
        for (int i = startBlock; i < startBlock + durationBlocks; i++) {
            if (diary.getBusyTimes().contains(i)) {
                log.info("m validateOverlapTime - request={} - status={}", request, FAILURE);
                throw new ScheduleOverlapTimeException();
            }
        }
    }

    private void setBusyTime(Diary diary, Appointment request) throws UpdateDiaryException {
        int startBlock = diary.getStartBlock(request.getStartAt());
        int durationBlocks = request.getDurationBlocks();
        for (int i = startBlock; i < startBlock + durationBlocks; i++) {
            diary.getBusyTimes().add(i);
        }
        try {
            updateDiaryUseCase.execute(diary);
        }catch (Exception e){
            log.info("m setBusyTime - request={} - status={}", request, FAILURE);
            throw new UpdateDiaryException();
        }
    }

    private void validateRequestServices(List<Integer> services, UUID appointmentId) throws ServiceIdNotFoundException {
        if(Objects.isNull(services) || services.isEmpty()){
            log.info("m validateRequestServices - appointmentId={} - status={}", appointmentId, FAILURE);
            throw new ServiceIdNotFoundException();
        }
//        serviceClient.servicesExistsById(services);
    }


}
