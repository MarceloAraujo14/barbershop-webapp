package com.barbershop.schedule.core.usecase.week;

import com.barbershop.schedule.core.entity.Diary;
import com.barbershop.schedule.core.port.repository.DiaryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.barbershop.schedule.core.entity.enums.StatusProcess.NEW;
import static com.barbershop.schedule.core.entity.enums.StatusProcess.SUCCESS;

@Log4j2
@Service
@AllArgsConstructor
public class GetCurrentWeeksUseCase {
    private final DiaryRepository diaryRepository;

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
