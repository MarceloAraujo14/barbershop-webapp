package com.barbershop.schedule.core.usecase.diary;

import com.barbershop.schedule.core.entity.Diary;
import com.barbershop.schedule.core.port.repository.DiaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UpdateDiaryUseCase {

    private final DiaryRepository diaryRepository;

    public Diary execute(Diary diary){
        return diaryRepository.save(diary);
    }
}
