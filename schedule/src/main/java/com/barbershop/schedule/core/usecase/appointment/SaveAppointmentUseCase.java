package com.barbershop.schedule.core.usecase.appointment;

import com.barbershop.schedule.core.entity.Appointment;
import com.barbershop.schedule.core.entity.Diary;
import com.barbershop.schedule.core.entity.enums.AppointmentStatus;
import com.barbershop.schedule.core.exception.OverlapTimeException;
import com.barbershop.schedule.core.exception.ScheduleAppointmentException;
import com.barbershop.schedule.core.port.repository.AppointmentRepository;
import com.barbershop.schedule.core.usecase.diary.GetDiaryUseCase;
import com.barbershop.schedule.core.usecase.diary.UpdateDiaryUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static com.barbershop.schedule.core.constants.AppointmentConstants.OVERLAP_TIME_APPOINTMENT_MSG;
import static com.barbershop.schedule.core.constants.AppointmentConstants.SCHEDULE_APPOINTMENT_FAIULE_MSG;
import static com.barbershop.schedule.core.entity.enums.StatusProcess.FAILURE;
import static com.barbershop.schedule.core.entity.enums.StatusProcess.PROCESSING;

@Log4j2
@Service
@AllArgsConstructor
public class SaveAppointmentUseCase {

    private final AppointmentRepository repository;

    private final GetDiaryUseCase getDiaryUseCase;
    private final UpdateDiaryUseCase updateDiaryUseCase;

    public Appointment execute(Appointment request) throws ScheduleAppointmentException {
        log.info("m save - request={} - status={}", request, PROCESSING);
        try {
            Diary diary = getDiaryUseCase.execute(request.getDate());
            validateOverlapTime(diary, request);
            setBusyTime(diary, request);
            //todo: save services id on services_appointment table
            updateDiaryUseCase.execute(diary);
            return repository.save(request);
        }catch (OverlapTimeException e){
            log.info("m save - request={} - status={}", request, FAILURE);
            request.setStatus(AppointmentStatus.FAILURE);
            repository.save(request);
            throw new ScheduleAppointmentException(SCHEDULE_APPOINTMENT_FAIULE_MSG);
        }
    }

    private void validateOverlapTime(Diary diary, Appointment request) throws OverlapTimeException {
        int startBlock = diary.getStartBlock(request.getStartAt());
        int durationBlocks = request.getDurationBlocks();
        for (int i = startBlock; i < startBlock + durationBlocks; i++) {
            if (diary.getBusyTimes().contains(i)) {
                log.info("m validateOverlapTime - request={} - status={}", request, FAILURE);
                throw new OverlapTimeException(OVERLAP_TIME_APPOINTMENT_MSG);
            }
        }
    }

    private void setBusyTime(Diary diary, Appointment request){
        int startBlock = diary.getStartBlock(request.getStartAt());
        int durationBlocks = request.getDurationBlocks();
        for (int i = startBlock; i < startBlock + durationBlocks; i++) {
            diary.getBusyTimes().add(i);
        }
    }


}
