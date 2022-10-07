package com.barbershop.schedule.core.usecase.week.contracts;

import com.barbershop.schedule.core.domain.Diary;

import java.util.List;

public interface GetCurrentWeeksUseCase {
    List<Diary> execute();
}
