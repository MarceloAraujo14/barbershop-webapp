package com.barbershop.schedule.core.port.dataprovider;

import com.barbershop.schedule.core.domain.Diary;

import java.time.LocalDate;
import java.util.List;

public interface DiaryRepository {

    void saveAll(List<Diary> week);

    Diary save(Diary diary);

    Diary getDiary(LocalDate date);

    List<Diary> getWeek(List<LocalDate> dates);
}
