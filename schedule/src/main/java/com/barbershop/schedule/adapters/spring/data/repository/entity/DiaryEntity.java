package com.barbershop.schedule.adapters.spring.data.repository.entity;

import com.barbershop.schedule.core.entity.Diary;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

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
    @Column(columnDefinition = "integer[]")

    @ElementCollection
    @CollectionTable(name = "diary_busy_times", joinColumns = @JoinColumn(name = "diary_date"))
    private Set<Integer> busyTimes = new LinkedHashSet<>();

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
