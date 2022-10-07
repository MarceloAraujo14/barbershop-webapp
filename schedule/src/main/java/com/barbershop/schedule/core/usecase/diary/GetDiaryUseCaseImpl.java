package com.barbershop.schedule.core.usecase.diary;

import com.barbershop.schedule.core.domain.Diary;
import com.barbershop.schedule.core.port.dataprovider.DiaryRepository;
import com.barbershop.schedule.core.domain.enums.StatusProcess;
import com.barbershop.schedule.core.usecase.diary.contracts.GetDiaryUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Log4j2
@AllArgsConstructor
@Service
public class GetDiaryUseCaseImpl implements GetDiaryUseCase {

    private final DiaryRepository diaryRepository;

    @Override
    public Diary execute(LocalDate date){
        log.info("m execute status={}", StatusProcess.NEW);
        return diaryRepository.getDiary(date);
    }

}
