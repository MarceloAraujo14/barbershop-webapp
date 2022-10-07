package com.barbershop.schedule.core.usecase.appointment;

import com.barbershop.schedule.core.domain.Appointment;
import com.barbershop.schedule.core.domain.Diary;
import com.barbershop.schedule.core.domain.enums.AppointmentStatus;
import com.barbershop.schedule.core.exception.OverlapTimeException;
import com.barbershop.schedule.core.exception.ScheduleAppointmentException;
import com.barbershop.schedule.core.port.client.ServiceClient;
import com.barbershop.schedule.core.port.dataprovider.AppointmentRepository;
import com.barbershop.schedule.core.usecase.appointment.contracts.SaveAppointmentUseCase;
import com.barbershop.schedule.core.usecase.diary.contracts.GetDiaryUseCase;
import com.barbershop.schedule.core.usecase.diary.contracts.UpdateDiaryUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.barbershop.schedule.core.domain.enums.StatusProcess.FAILURE;
import static com.barbershop.schedule.core.domain.enums.StatusProcess.PROCESSING;

@Log4j2
@Service
@AllArgsConstructor
public class SaveAppointmentUseCaseImpl implements SaveAppointmentUseCase {

    private final AppointmentRepository repository;
//    private final ServiceClient serviceClient;
    private final GetDiaryUseCase getDiaryUseCase;
    private final UpdateDiaryUseCase updateDiaryUseCase;

    @Override
    public Appointment execute(Appointment request) throws ScheduleAppointmentException {
        log.info("m save - request={} - status={}", request, PROCESSING);
        try {
            Diary diary = getDiaryUseCase.execute(request.getDate());
//            validateRequestServices(request.getServiceIds());
            validateOverlapTime(diary, request);
            request.setStatus(AppointmentStatus.SCHEDULED);
            Appointment appointment = repository.save(request);
            setBusyTime(diary, request);
            return appointment;
        }catch (OverlapTimeException e){
            log.info("m save - request={} - exception={} - status={} ", request, e.getMessage(), FAILURE);
            request.setStatus(AppointmentStatus.FAILURE);
            repository.save(request);
            throw new ScheduleAppointmentException();
        }
    }

    private void validateOverlapTime(Diary diary, Appointment request) throws OverlapTimeException {
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

//    private void validateRequestServices(List<Integer> services){
//        serviceClient.servicesExistsById(services);
//    }


}
