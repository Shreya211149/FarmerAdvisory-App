package com.shreya.farmeradvisory.transformer;

import com.shreya.farmeradvisory.dto.request.WeatherDataRequest;
import com.shreya.farmeradvisory.dto.response.WeatherDataResponse;
import com.shreya.farmeradvisory.models.WeatherData;

public class WeatherDataTransformer {
    public static WeatherData toEntity(WeatherDataRequest request) {
        return WeatherData.builder()
                .district(request.getDistrict())
                .rainfallMm(request.getRainfallMm())
                .floodRisk(request.getFloodRisk())
                .build();
    }

    public static WeatherDataResponse toResponse(WeatherData data) {
        return WeatherDataResponse.builder()
                .id(data.getId())
                .district(data.getDistrict())
                .rainfallMm(data.getRainfallMm())
                .floodRisk(data.getFloodRisk())
                .recordedAt(data.getRecordedAt())
                .build();

    }
}
