package com.barbershop.schedule.core.domain;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Builder
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Diary implements Comparable<Diary>{
    @Getter
    private LocalDate date;
    @Setter @Getter
    private Set<Integer> busyTimes = new LinkedHashSet<>();
    @ToString.Exclude
    private final List<String> hourList =
            List.of("08:00","08:15","08:30","08:45","09:00","09:15","09:30","09:45","10:00","10:15","10:30","10:45",
                    "11:00","11:15","11:30","11:45","13:00","13:15","13:30","13:45","14:00","14:15","14:30","14:45",
                    "15:00","15:15","15:30","15:45","16:00","16:15","16:30","16:45","17:00","17:15","17:30","17:45");

    public Diary(LocalDate date, Set<Integer> busyTimes) {
        this.date = date;
        this.busyTimes.addAll(busyTimes);
    }

    public Diary(LocalDate date) {
        this.date = date;
        setBusyTime();
    }

    public int getStartBlock(LocalTime time){
        time = roundHour(time);
        return this.hourList.indexOf(time.format(DateTimeFormatter.ofPattern("HH:mm")));
    }

    public boolean isAvailable(){
        return busyTimes.size() < 36 && (date.isEqual(LocalDate.now()) || date.isAfter(LocalDate.now())) && date.getDayOfWeek().getValue() != 1;
    }

    public LocalDate getWeekTuesday(){
        int today = date.getDayOfWeek().getValue();
        if(today > 2) return date.minusDays(daysTillTuesday(today));
        return date.plusDays(daysTillTuesday(today));
    }

    private int daysTillTuesday(int today){
        if (today > 2) return today - 2;
        return 2 - today;
    }

    private void setBusyTime(){
        if(!date.isEqual(LocalDate.now()) || !isBusinessTime()) return;
        int busyTimeBlock = getStartBlock(LocalTime.now());
        for (int i = 0; i <= busyTimeBlock; i++) {
            this.busyTimes.add(i);
        }
    }

    private boolean isBusinessTime(){
        return (LocalTime.now().isBefore(LocalTime.of(17,45)));
    }

    @Override
    public int compareTo(Diary d) {
        return this.date.isAfter(d.date)? -1 : 1;
    }

    private LocalTime roundHour(LocalTime time){
        if(time.getMinute() < 15) return LocalTime.of(time.getHour(), 15);
        if(time.getMinute() < 30) return LocalTime.of(time.getHour(), 30);
        if(time.getMinute() < 45) return LocalTime.of(time.getHour(), 45);
        return LocalTime.of(time.getHour()+1, 0);
    }
}

