package com.barbershop.schedule.core.core.usecase.week;

import com.barbershop.schedule.core.entity.Diary;
import com.barbershop.schedule.core.port.repository.DiaryRepository;
import com.barbershop.schedule.core.usecase.week.GetCurrentWeeksUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetCurrentWeeksUseCaseTest {

    @InjectMocks
    private GetCurrentWeeksUseCase getCurrentWeeksUseCase;

    @Mock
    DiaryRepository repository;

    @Test
    void shouldGetCurrentWeeks(){
        List<Diary> diaries = new ArrayList<>(getCurrentWeek());
        diaries.addAll(getNextWeek());
        when(repository.getWeek(getWeeks())).thenReturn(diaries);
        getCurrentWeeksUseCase.execute();

    }

    private List<Diary> getCurrentWeek(){

        return List.of(
                new Diary(LocalDate.now()),
                new Diary(LocalDate.now().plusDays(1)),
                new Diary(LocalDate.now().plusDays(2)),
                new Diary(LocalDate.now().plusDays(3)),
                new Diary(LocalDate.now().plusDays(4)),
                new Diary(LocalDate.now().plusDays(5))
        );
    }

    private List<Diary> getNextWeek(){

        return List.of(
                new Diary(LocalDate.now().plusWeeks(1)),
                new Diary(LocalDate.now().plusWeeks(1).plusDays(1)),
                new Diary(LocalDate.now().plusWeeks(1).plusDays(2)),
                new Diary(LocalDate.now().plusWeeks(1).plusDays(3)),
                new Diary(LocalDate.now().plusWeeks(1).plusDays(4)),
                new Diary(LocalDate.now().plusWeeks(1).plusDays(5))
        );
    }

    private List<LocalDate> getWeeks(){
        var tuesday = new Diary(LocalDate.now()).getWeekTuesday();
        List<LocalDate> week = new ArrayList<>(6);

        for (int i = 0; i < 13; i++) {
            if(i == 6) continue;
            week.add(tuesday.plusDays(i));
        }
        return week;
    }
}