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
import java.util.List;
import java.util.Objects;

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
    public Appointment execute(Appointment request) throws ScheduleAppointmentException, OverlapTimeException, InvalidScheduleDateException, ServiceIdNotFoundException {
        log.info("m execute - request={} - status={}", request, PROCESSING);
        try {
            validDateAppointment(request.getDate());
            Diary diary = getDiaryUseCase.execute(request.getDate());
            validateOverlapBusyTime(diary, request);
            validateRequestServices(request.getServiceIds());
            request.setStatus(AppointmentStatus.SCHEDULED);
            Appointment appointment = repository.save(request);
            setBusyTime(diary, request);
            return appointment;
        }catch (ScheduleException e){
            log.info("m execute - request={} - exception={} - status={} ", request, e.getMessage(), FAILURE);
            request.setStatus(AppointmentStatus.FAILURE);
            repository.save(request);
            throw e;
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

    private void validateOverlapBusyTime(Diary diary, Appointment request) throws OverlapTimeException {
        int startBlock = diary.getStartBlock(request.getStartAt());
        int durationBlocks = request.getDurationBlocks();
        for (int i = startBlock; i < startBlock + durationBlocks; i++) {
            if (diary.getBusyTimes().contains(i)) {
                log.info("m validateOverlapTime - request={} - status={}", request, FAILURE);
                throw new OverlapTimeException();
            }
        }
    }

    private void setBusyTime(Diary diary, Appointment request){
        int startBlock = diary.getStartBlock(request.getStartAt());
        int durationBlocks = request.getDurationBlocks();
        for (int i = startBlock; i < startBlock + durationBlocks; i++) {
            diary.getBusyTimes().add(i);
        }
        updateDiaryUseCase.execute(diary);
    }

    private void validateRequestServices(List<Integer> services) throws ServiceIdNotFoundException {
        if(Objects.isNull(services) || services.isEmpty()){
            throw new ServiceIdNotFoundException();
        }
//        serviceClient.servicesExistsById(services);
    }


}
