package com.barbershop.schedule.core.usecase.diary;

import com.barbershop.schedule.core.domain.Diary;
import com.barbershop.schedule.core.port.dataprovider.DiaryRepository;
import com.barbershop.schedule.core.usecase.diary.contracts.UpdateDiaryUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UpdateDiaryUseCaseImpl implements UpdateDiaryUseCase {

    private final DiaryRepository diaryRepository;

    @Override
    public Diary execute(Diary diary){
        return diaryRepository.save(diary);
    }
}
