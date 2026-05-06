package com.shreya.farmeradvisory.service;

import com.shreya.farmeradvisory.dto.response.WeatherDataResponse;
import com.shreya.farmeradvisory.exception.DistrictNotSupportedException;
import com.shreya.farmeradvisory.models.WeatherData;
import com.shreya.farmeradvisory.repo.WeatherDataRepository;
import com.shreya.farmeradvisory.transformer.WeatherDataTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class WeatherApiService {

    private final RestTemplate restTemplate;
    private final WeatherDataRepository weatherRepository;

    @Value("${openweather.api.key}")
    private String apiKey;

    @Value("${openweather.api.url}")
    private String apiUrl;

    // District → latitude, longitude
    private static final Map<String, double[]> DISTRICT_COORDS = Map.of(
            "Kolkata", new double[]{22.5726, 88.3639},
            "Hooghly", new double[]{22.9050, 88.3960},
            "Paschim Medinipur", new double[]{22.4080, 87.3811},
            "Purba Medinipur", new double[]{21.9333, 87.7833},
            "Murshidabad", new double[]{24.1833, 88.2667},
            "Nadia", new double[]{23.4000, 88.5000},
            "South 24 Parganas", new double[]{22.2000, 88.4000}
    );



    public WeatherDataResponse fetchAndSaveWeather(String district) {
        district = district.trim();

        double[] coords = DISTRICT_COORDS.get(district);
        if (coords == null) {
            throw new DistrictNotSupportedException("Unsupported district: " + district);
        }

        String url = UriComponentsBuilder.fromUriString(apiUrl)
                .queryParam("lat", coords[0])
                .queryParam("lon", coords[1])
                .queryParam("appid", apiKey)
                .queryParam("units", "metric")
                .toUriString();

        Map<String, Object> body;
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
            body = response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Weather API failed for " + district);
        }

        // Rainfall parsing
        double rainfallMm = 0.0;
        if (body != null && body.containsKey("rain")) {
            Map<String, Object> rain = (Map<String, Object>) body.get("rain");
            if (rain.containsKey("1h")) {
                rainfallMm = ((Number) rain.get("1h")).doubleValue();
            }
        }

        String floodRisk = determineFloodRisk(rainfallMm);

        WeatherData data = WeatherData.builder()
                .district(district)
                .rainfallMm(rainfallMm)
                .floodRisk(floodRisk)
                .build();

        return WeatherDataTransformer.toResponse(
                weatherRepository.save(data)
        );
    }

    private String determineFloodRisk(double rainfallMm) {
        if (rainfallMm > 80) return "EXTREME";
        if (rainfallMm > 50) return "HIGH";
        if (rainfallMm > 20) return "MEDIUM";
        return "LOW";
    }
}