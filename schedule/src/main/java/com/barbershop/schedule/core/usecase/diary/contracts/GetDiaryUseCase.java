package com.barbershop.schedule.core.usecase.diary.contracts;

import com.barbershop.schedule.core.domain.Diary;

import java.time.LocalDate;

public interface GetDiaryUseCase {
    Diary execute(LocalDate date);
}
