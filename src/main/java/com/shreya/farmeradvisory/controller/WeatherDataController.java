package com.shreya.farmeradvisory.controller;

import com.shreya.farmeradvisory.dto.response.WeatherDataResponse;
import com.shreya.farmeradvisory.dto.response.WeatherForecastResponse;
import com.shreya.farmeradvisory.service.WeatherApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherDataController {

    //private final WeatherDataService weather-Service;
    private final WeatherApiService weatherApiService;

    // Existing endpoints ...

//    @GetMapping("/latest/{district}")
//    public WeatherDataResponse latestWeather(@PathVariable String district) {
//        return weatherApiService.getLatestWeather(district);
//    }

    // Manually trigger a live fetch for any district
    @PostMapping("/fetch/{district}")
    public WeatherDataResponse fetchLive(@PathVariable String district) {
        return weatherApiService.fetchAndSaveWeather(district);
    }
    @GetMapping("/forecast/public/{district}")
    public WeatherForecastResponse getPublicForecast(
            @PathVariable String district) {
        return weatherApiService.fetchSevenDayForecast(district);
    }
    @GetMapping("/forecast/{district}")
    public WeatherForecastResponse getForecast(
            @PathVariable String district) {
        return weatherApiService.fetchSevenDayForecast(district);
    }
}
