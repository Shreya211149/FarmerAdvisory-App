package com.shreya.farmeradvisory.scheduler;

import com.shreya.farmeradvisory.service.WeatherApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WeatherScheduler {

    private final WeatherApiService weatherApiService;

    // Runs every 3 hours automatically
    // cron format: second minute hour day month weekday
    @Scheduled(cron = "0 0 */3 * * *")
    public void fetchWeatherForAllDistricts() {
        List<String> districts = List.of(
                "Kolkata",
                "Hooghly",
                "Purba Medinipur",
                "Murshidabad",
                "Nadia",
                "South 24 Parganas",
                "Paschim Medinipur"
        );

        for (String district : districts) {
            try {
                weatherApiService.fetchAndSaveWeather(district);
                System.out.println("Weather fetched for: " + district);
            } catch (Exception e) {
                // If one district fails, continue with the rest
                System.out.println("Failed to fetch weather for: " + district + " — " + e.getMessage());
            }
        }
    }
}
