package com.shreya.farmeradvisory.controller;

import com.shreya.farmeradvisory.dto.response.WeatherDataResponse;
import com.shreya.farmeradvisory.service.WeatherApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherDataController {

    //private final WeatherDataService weather-Service;
    private final WeatherApiService weatherApiService;

    // Existing endpoints ...

    // Manually trigger a live fetch for any district
    @PostMapping("/fetch/{district}")
    public WeatherDataResponse fetchLive(@PathVariable String district) {
        return weatherApiService.fetchAndSaveWeather(district);
    }
}
