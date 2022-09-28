package com.barbershop.schedule.core.usecase.diary;

import com.barbershop.schedule.core.entity.Diary;
import com.barbershop.schedule.core.port.repository.DiaryRepository;
import com.barbershop.schedule.core.entity.enums.StatusProcess;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Log4j2
@AllArgsConstructor
@Service
public class GetDiaryUseCase {

    private final DiaryRepository diaryRepository;

    public Diary execute(LocalDate date){
        log.info("m execute status={}", StatusProcess.NEW);
        return diaryRepository.getDiary(date);
    }

}
