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
        assertEquals(getCurrentWeek(), actual);
    }

    @Test
    void shouldGenerateNextWeek() {
        List<Diary> actual = generateNextWeek.execute();
        assertEquals(getNextWeek(), actual);
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
}