package com.barbershop.schedule.core.usecase.week;

import com.barbershop.schedule.core.domain.Diary;
import com.barbershop.schedule.core.port.dataprovider.DiaryRepository;
import com.barbershop.schedule.core.usecase.week.contracts.GenerateCurrentWeekUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.barbershop.schedule.core.domain.enums.StatusProcess.NEW;

@Log4j2
@AllArgsConstructor
@Service
public class GenerateCurrentWeekUseCaseImpl implements GenerateCurrentWeekUseCase {

    private final DiaryRepository diaryRepository;

    @Override
    public List<Diary> execute(){
        log.info("m execute status={}", NEW );
        var tuesday = new Diary(LocalDate.now()).getWeekTuesday();
        List<Diary> week = new ArrayList<>(6);

        for (int i = 0; i < 6; i++) {
            Diary day = new Diary(tuesday.plusDays(i));
            week.add(day);
        }
        diaryRepository.saveAll(week);
        return week;
    }

}
