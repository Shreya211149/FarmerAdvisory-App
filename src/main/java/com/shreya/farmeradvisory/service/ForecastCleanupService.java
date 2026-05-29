package com.shreya.farmeradvisory.service;

import com.shreya.farmeradvisory.repo.WeatherDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ForecastCleanupService {

    private final WeatherDataRepository forecastRepository;

    public void deleteExpiredForecasts() {

        LocalDateTime cutoff = LocalDateTime.now().minusDays(7); //  keep last 7 days

        forecastRepository.deleteByRecordedAtBefore(cutoff);

        System.out.println("Old weather data deleted before: " + cutoff);

    }
}
