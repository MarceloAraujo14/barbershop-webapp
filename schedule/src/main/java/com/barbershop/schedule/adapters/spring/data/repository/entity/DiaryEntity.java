package com.barbershop.schedule.adapters.spring.data.repository.entity;

import com.barbershop.schedule.core.entity.Diary;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@Entity(name = "diary")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DiaryEntity {
    @Id
    private LocalDate date;
    @ElementCollection
    @CollectionTable(name = "busy_times", joinColumns = @JoinColumn(name = "date"))
    private List<Integer> busyTimes = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DiaryEntity that = (DiaryEntity) o;
        return date != null && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Diary getDiary(){
        return Diary.builder()
                .date(this.date)
                .busyTimes(this.busyTimes)
                .build();
    }

    public static DiaryEntity getEntity(Diary diary){
        return DiaryEntity.builder()
                .date(diary.getDate())
                .busyTimes(diary.getBusyTimes())
                .build();
    }
}
