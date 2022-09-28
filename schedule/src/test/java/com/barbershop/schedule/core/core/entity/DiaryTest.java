package com.barbershop.schedule.core.core.entity;

import com.barbershop.schedule.core.entity.Diary;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiaryTest {

    @Test
    void getBusyTime(){
        int busyTime = getToday().getStartBlock(LocalTime.of(8, 0));
        assertEquals(0, busyTime);
    }

    @Test
    void createDiary(){
        Diary diary = new Diary(LocalDate.now(), List.of(1,2,3));
        assertEquals(getToday(), diary);
    }

    @Test
    void shouldBeAvailable(){
        assertTrue(getTomorrow().isAvailable());
    }

    @Test
    void yesterdayShouldNotBeAvailable(){
        System.out.println(getYesterday());
        assertFalse(getYesterday().isAvailable());
    }

    @Test
    void getWeekTuesday(){
        assertEquals(DayOfWeek.TUESDAY, getToday().getWeekTuesday().getDayOfWeek());
    }

    private Diary getToday(){
        return Diary.builder()
                .date(LocalDate.now())
                .busyTimes(List.of(1,2,3))
                .build();
    }

    private Diary getTomorrow(){
        return Diary.builder()
                .date(LocalDate.now().plusDays(1))
                .busyTimes(List.of())
                .build();
    }

    private Diary getYesterday(){
        return new Diary(LocalDate.now().minusDays(1), List.of());
    }

}
