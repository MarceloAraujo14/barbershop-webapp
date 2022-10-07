package com.barbershop.schedule.core.usecase.week;

import com.barbershop.schedule.core.domain.Diary;
import com.barbershop.schedule.core.port.dataprovider.DiaryRepository;
import com.barbershop.schedule.core.usecase.week.contracts.GetCurrentWeeksUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.barbershop.schedule.core.domain.enums.StatusProcess.NEW;
import static com.barbershop.schedule.core.domain.enums.StatusProcess.SUCCESS;

@Log4j2
@Service
@AllArgsConstructor
public class GetCurrentWeeksUseCaseImpl implements GetCurrentWeeksUseCase {
    private final DiaryRepository diaryRepository;

    @Override
    public List<Diary> execute(){
        log.info("m execute status={}", NEW);
        var tuesday = new Diary(LocalDate.now()).getWeekTuesday();
        List<LocalDate> week = new ArrayList<>(6);

        for (int i = 0; i < 13; i++) {
            if(i == 6) continue;
            week.add(tuesday.plusDays(i));
        }

        List<Diary> weeks = diaryRepository.getWeek(week);
        log.info("m execute - weeks={} -  status={}",weeks, SUCCESS);
        return weeks;
    }

}
