package com.barbershop.schedule.core.core.usecase.week;

import com.barbershop.schedule.core.entity.Diary;
import com.barbershop.schedule.core.port.repository.DiaryRepository;
import com.barbershop.schedule.core.usecase.week.GenerateCurrentWeekUseCase;
import com.barbershop.schedule.core.usecase.week.GenerateNextWeekUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(value = MockitoExtension.class)
class GenerateWeekTest {

    @InjectMocks
    private GenerateCurrentWeekUseCase generateCurrentWeekUseCase;
    @InjectMocks
    private GenerateNextWeekUseCase generateNextWeek;

    @Mock
    private DiaryRepository repository;

    @Test
    void shouldGenerateCurrentWeek() {
        List<Diary> actual = generateCurrentWeekUseCase.execute();
        System.out.println(actual);
        assertEquals(getCurrentWeek(), actual);
    }

    @Test
    void shouldGenerateNextWeek() {
        List<Diary> actual = generateNextWeek.execute();
        assertEquals(getNextWeek(), actual);
    }

    private List<Diary> getCurrentWeek(){
        Diary diary = new Diary(LocalDate.now());
        return List.of(
                new Diary(diary.getWeekTuesday()),
                new Diary(diary.getWeekTuesday().plusDays(1)),
                new Diary(diary.getWeekTuesday().plusDays(2)),
                new Diary(diary.getWeekTuesday().plusDays(3)),
                new Diary(diary.getWeekTuesday().plusDays(4)),
                new Diary(diary.getWeekTuesday().plusDays(5))
        );
    }

    private List<Diary> getNextWeek(){
        Diary diary = new Diary(LocalDate.now());
        return List.of(
                new Diary(diary.getWeekTuesday().plusWeeks(1)),
                new Diary(diary.getWeekTuesday().plusWeeks(1).plusDays(1)),
                new Diary(diary.getWeekTuesday().plusWeeks(1).plusDays(2)),
                new Diary(diary.getWeekTuesday().plusWeeks(1).plusDays(3)),
                new Diary(diary.getWeekTuesday().plusWeeks(1).plusDays(4)),
                new Diary(diary.getWeekTuesday().plusWeeks(1).plusDays(5))
        );
    }
}