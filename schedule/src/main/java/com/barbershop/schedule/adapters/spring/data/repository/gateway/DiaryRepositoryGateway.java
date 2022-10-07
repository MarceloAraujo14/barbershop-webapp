package com.barbershop.schedule.adapters.spring.data.repository.gateway;


import com.barbershop.schedule.adapters.spring.data.repository.DiaryJpaRepository;
import com.barbershop.schedule.adapters.spring.data.repository.entity.DiaryEntity;
import com.barbershop.schedule.core.domain.Diary;
import com.barbershop.schedule.core.port.dataprovider.DiaryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.barbershop.schedule.core.domain.enums.StatusProcess.NEW;
import static com.barbershop.schedule.core.domain.enums.StatusProcess.SUCCESS;

@Log4j2
@Service
@AllArgsConstructor
public class DiaryRepositoryGateway implements DiaryRepository {

    private final DiaryJpaRepository diaryRepository;

    @Override
    public void saveAll(List<Diary> week) {
        log.info("m saveAll week={}, status={}", week, NEW);
        List<DiaryEntity> entityList = week.stream().map(DiaryEntity::getEntity).collect(Collectors.toList());
        diaryRepository.saveAll(entityList);
        log.info("m saveAll status={}", SUCCESS);
    }

    @Override
    public Diary save(Diary diary) {
        log.info("m save - diary={} - status={}",diary, NEW);
        diaryRepository.save(DiaryEntity.getEntity(diary));
        log.info("m save status={}", SUCCESS);
        return diary;
    }

    @Override
    public Diary getDiary(LocalDate date) {
        log.info("m getDiary - date={} - status={}",date, NEW);
        DiaryEntity entity = diaryRepository.findById(date).orElseThrow(EntityNotFoundException::new);
        log.info("m getDiary entity={} - status={}",entity, SUCCESS);
        return entity.getDiary();
    }

    @Override
    public List<Diary> getWeek(List<LocalDate> dates) {
        log.info("m getWeek - dates={} - status={}",dates, NEW);
        var week = diaryRepository.findAllById(dates);
        log.info("m getWeek week={} - status={}",week, SUCCESS);
        return week.stream().map(DiaryEntity::getDiary).collect(Collectors.toList());
    }
}
