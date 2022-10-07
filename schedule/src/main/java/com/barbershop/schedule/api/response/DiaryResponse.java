package com.barbershop.schedule.api.response;

import com.barbershop.schedule.core.entity.Diary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DiaryResponse {
    private String day;
    private Set<Integer> busyTimes = new LinkedHashSet<>();
    private boolean isAvailable;

    public static DiaryResponse getResponse(Diary diary){
        return DiaryResponse.builder()
                .day(diary.getDate().format(DateTimeFormatter.ofPattern("dd")))
                .busyTimes(diary.getBusyTimes())
                .isAvailable(diary.isAvailable())
                .build();
    }
}
