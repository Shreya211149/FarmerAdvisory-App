package com.shreya.farmeradvisory.test.contoller;

import com.shreya.farmeradvisory.scheduler.DailyWeatherScheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestSchedulerController {

    private final DailyWeatherScheduler scheduler;

    @PostMapping("/run-scheduler")
    public String runSchedulerNow() {
        scheduler.runDailyWeatherCheck();
        return "Scheduler executed manually";
    }
}