package com.barbershop.schedule.api.resource;

import com.barbershop.schedule.api.response.DiaryResponse;
import com.barbershop.schedule.core.entity.Diary;
import com.barbershop.schedule.core.service.DiaryService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.barbershop.schedule.core.entity.enums.StatusProcess.NEW;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/week")
public class WeekGenerateResource {
    private final DiaryService diaryService;

    @PostMapping(value = "/load", consumes = "application/json", produces = "application/json")
    public void generateOnFirstLoad(){
        log.info("m generateOnFirstLoad status={}", NEW);
        diaryService.generateFirstWeeks();
    }

    @GetMapping(consumes = "application/json", produces = "application/json")
    public List<DiaryResponse> getWeeks(){
        return diaryService.getCurrentWeeks().stream().map(DiaryResponse::getResponse).toList();
    }

}
