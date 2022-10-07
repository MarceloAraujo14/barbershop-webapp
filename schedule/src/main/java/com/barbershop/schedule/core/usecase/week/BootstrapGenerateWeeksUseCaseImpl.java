package com.barbershop.schedule.core.usecase.week;

import com.barbershop.schedule.core.domain.Diary;
import com.barbershop.schedule.core.usecase.diary.contracts.GetDiaryUseCase;
import com.barbershop.schedule.core.usecase.week.contracts.BootstrapGenerateWeeksUseCase;
import com.barbershop.schedule.core.usecase.week.contracts.GenerateCurrentWeekUseCase;
import com.barbershop.schedule.core.usecase.week.contracts.GenerateNextWeekUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

import static com.barbershop.schedule.core.domain.enums.StatusProcess.*;

@Log4j2
@Service
@AllArgsConstructor
public class BootstrapGenerateWeeksUseCaseImpl implements BootstrapGenerateWeeksUseCase {

    private final GenerateCurrentWeekUseCase generateCurrentWeekUseCase;
    private final GenerateNextWeekUseCase generateNextWeekUseCase;
    private final GetDiaryUseCase getDiaryUseCase;

    @Override
    public void execute(){
        log.info("m execute status={}", NEW);
        generateCurrentWeek();
        generateNextWeek();
    }

    private void generateCurrentWeek(){
        Diary today = null;
        try{
            today = getDiaryUseCase.execute(LocalDate.now());
            log.info("m generateCurrentWeek status={}", FAILURE);

        }catch (Exception e){
            log.info("m generateCurrentWeek - Semana não existe no banco. Gerando Semana atual... status={}", PROCESSING);
        }
        if (Objects.isNull(today)){
            generateCurrentWeekUseCase.execute();
            generateNextWeekUseCase.execute();
            log.info("m generateCurrentWeek status={}", SUCCESS);
        }
    }

    private void generateNextWeek() {
        Diary nextWeek = null;
        try{
            nextWeek = getDiaryUseCase.execute(LocalDate.now().plusWeeks(1));
            log.info("m generateNextWeek status={}", FAILURE);

        }catch (Exception e){
            log.info("m generateNextWeek - Semana não existe no banco. Gerando próxima semana... status={}", PROCESSING);
        }
        if (Objects.isNull(nextWeek)){
            generateNextWeekUseCase.execute();
            log.info("m generateNextWeek status={}", SUCCESS);
        }
    }
}
