package com.barbershop.schedule.api.resource;

import com.barbershop.schedule.api.response.DiaryResponse;
import com.barbershop.schedule.core.usecase.week.contracts.BootstrapGenerateWeeksUseCase;
import com.barbershop.schedule.core.usecase.week.contracts.GetCurrentWeeksUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.barbershop.schedule.core.domain.enums.StatusProcess.NEW;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/week")
public class WeekGenerateResource {
    private final BootstrapGenerateWeeksUseCase bootstrapGenerateWeeksUseCaseImpl;
    private final GetCurrentWeeksUseCase getCurrentWeeksUseCase;

    @PostMapping(value = "/load")
    public void generateOnFirstLoad(){
        log.info("m generateOnFirstLoad status={}", NEW);
        bootstrapGenerateWeeksUseCaseImpl.execute();
    }

    @GetMapping(produces = "application/json")
    public List<DiaryResponse> getWeeks(){
        return getCurrentWeeksUseCase.execute().stream().map(DiaryResponse::getResponse).collect(Collectors.toList());
    }

}
