package com.barbershop.schedule.core.domain;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DiaryTest {

    @Test
    void getBusyTime(){
        int busyTime = getToday().getStartBlock(LocalTime.of(8, 0));
        assertEquals(0, busyTime);
    }

    @Test
    void createDiary(){
        Diary diary = new Diary(LocalDate.now(), Set.of(1,2,3));
        assertEquals(getToday(), diary);
    }

    @Test
    void shouldBeAvailable(){
        assertTrue(getTomorrow().isAvailable());
        assertTrue(getToday().isAvailable());
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

    @Test
    void shouldSetBusyTime(){
        Diary diary = new Diary(LocalDate.now());
        Set<Integer> busyTimes = diary.getBusyTimes();
        System.out.println(busyTimes);
        assertFalse(busyTimes.isEmpty());
    }

    private Diary getToday(){
        return Diary.builder()
                .date(LocalDate.now())
                .busyTimes(Set.of(1,2,3))
                .build();
    }

    private Diary getTomorrow(){
        return Diary.builder()
                .date(LocalDate.now().plusDays(1))
                .busyTimes(Set.of())
                .build();
    }

    private Diary getYesterday(){
        return new Diary(LocalDate.now().minusDays(1), Set.of());
    }

}
