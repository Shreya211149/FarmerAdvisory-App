package com.shreya.farmeradvisory.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WeatherDataRequest {
    private String district;
    private Double rainfallMm;
    private String floodRisk;
}