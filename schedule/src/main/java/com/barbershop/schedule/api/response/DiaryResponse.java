package com.barbershop.schedule.api.response;

import com.barbershop.schedule.core.entity.Diary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DiaryResponse {
    private String day;
    private List<Integer> busyTimes = new ArrayList<>();
    private boolean isAvailable;

    public static DiaryResponse getResponse(Diary diary){
        return DiaryResponse.builder()
                .day(diary.getDate().format(DateTimeFormatter.ofPattern("dd")))
                .busyTimes(diary.getBusyTimes())
                .isAvailable(diary.isAvailable())
                .build();
    }
}
