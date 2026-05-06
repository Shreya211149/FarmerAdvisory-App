package com.shreya.farmeradvisory.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class WeatherDataResponse {
    private Long id;
    private String district;
    private Double rainfallMm;
    private String floodRisk;
    private LocalDateTime recordedAt;
}
