package com.shreya.farmeradvisory.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherForecastResponse {

    private String                     district;
    private double                     currentRainfallMm;
    private String                     currentFloodRisk;
    private List<Map<String, Object>>  dailyForecast;
    private LocalDateTime              fetchedAt;
}