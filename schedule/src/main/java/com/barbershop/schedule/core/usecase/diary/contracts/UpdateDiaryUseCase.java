package com.barbershop.schedule.core.usecase.diary.contracts;

import com.barbershop.schedule.core.domain.Diary;

public interface UpdateDiaryUseCase {
    Diary execute(Diary diary);
}
