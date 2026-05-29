package com.shreya.farmeradvisory.scheduler;

import com.shreya.farmeradvisory.service.ForecastCleanupService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ForecastCleanupScheduler {

        private final ForecastCleanupService cleanupService;

        // Runs every day at 00:05 AM
        @Scheduled(cron = "0 5 0 * * *")
        public void cleanupOldForecasts() {
            cleanupService.deleteExpiredForecasts();
        }
    }

