package com.barbershop.schedule.core.service;

import com.barbershop.schedule.core.entity.Diary;
import com.barbershop.schedule.core.usecase.diary.GetDiaryUseCase;
import com.barbershop.schedule.core.usecase.diary.UpdateDiaryUseCase;
import com.barbershop.schedule.core.usecase.week.GenerateCurrentWeekUseCase;
import com.barbershop.schedule.core.usecase.week.GenerateNextWeekUseCase;
import com.barbershop.schedule.core.usecase.week.GetCurrentWeeksUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static com.barbershop.schedule.core.entity.enums.StatusProcess.*;

@Log4j2
@Service
@AllArgsConstructor
public class DiaryService {

    private final GenerateCurrentWeekUseCase generateCurrentWeekUseCase;
    private final GenerateNextWeekUseCase generateNextWeekUseCase;
    private final GetCurrentWeeksUseCase getCurrentWeeksUseCase;
    private final GetDiaryUseCase getDiaryUseCase;
    private final UpdateDiaryUseCase updateDiaryUseCase;

    public void generateFirstWeeks(){
        log.info("m generateFirstWeeks status={}", NEW);
        Diary diary = null;
        try{
            diary = getDiaryUseCase.execute(LocalDate.now());
            log.info("m generateFirstWeeks status={}", FAILURE);

        }catch (Exception e){
            log.info("m generateFirstWeeks - Semana não existe no banco. Gerando Semana atual e a seguinte... status={}", PROCESSING);
        }
        if (Objects.isNull(diary)){
            generateCurrentWeekUseCase.execute();
            generateNextWeekUseCase.execute();
            log.info("m generateFirstWeeks status={}", SUCCESS);
        }

    }

    public List<Diary> getCurrentWeeks(){
        return getCurrentWeeksUseCase.execute();
    }

}
