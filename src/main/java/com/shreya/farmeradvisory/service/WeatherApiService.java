package com.shreya.farmeradvisory.service;

import com.shreya.farmeradvisory.dto.response.WeatherDataResponse;
import com.shreya.farmeradvisory.dto.response.WeatherForecastResponse;
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

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



@Service
@RequiredArgsConstructor
public class WeatherApiService {
    private final RestTemplate restTemplate;
    private final WeatherDataRepository weatherRepository;

    // Open-Meteo base URL — no API key needed
    private static final String OPEN_METEO_URL =
            "https://api.open-meteo.com/v1/forecast";

    // District coordinates — geographic centre of each district
    private static final Map<String, double[]> DISTRICT_COORDS =
            Map.ofEntries(
                    Map.entry("Purba Medinipur",   new double[]{22.3008, 87.9259}),
                    Map.entry("Paschim Medinipur", new double[]{22.4200, 87.3200}),
                    Map.entry("Hooghly",           new double[]{22.9010, 88.3953}),
                    Map.entry("Murshidabad",       new double[]{24.1800, 88.2700}),
                    Map.entry("Nadia",             new double[]{23.4000, 88.5500}),
                    Map.entry("North 24 Parganas", new double[]{22.7200, 88.4000}),
                    Map.entry("South 24 Parganas", new double[]{22.1576, 88.6342}),
                    Map.entry("Howrah",            new double[]{22.5958, 88.2636}),
                    Map.entry("Bankura",           new double[]{23.2300, 87.0700}),
                    Map.entry("Birbhum",           new double[]{23.9000, 87.5300}),
                    Map.entry("Purba Bardhaman",   new double[]{23.2324, 87.8615}),
                    Map.entry("Paschim Bardhaman", new double[]{23.6800, 87.1000})
            );

    private double[] getCoords(String district) {
        return DISTRICT_COORDS.getOrDefault(
                district,
                new double[]{22.5726, 88.3639}  // Kolkata fallback
        );
    }
    private String determineFloodRisk(double rainfallMm) {
        if (rainfallMm > 80) return "EXTREME";
        if (rainfallMm > 50) return "HIGH";
        if (rainfallMm > 20) return "MEDIUM";
        return "LOW";
    }
    // ── fetchAndSaveWeather — called by both schedulers ──
    // Now uses Open-Meteo instead of OpenWeatherMap
    // Returns a single WeatherDataResponse (today only)
    public WeatherDataResponse fetchAndSaveWeather(String district) {
        // Reuse fetchSevenDayForecast — it already saves today to DB
        // and returns currentRainfallMm + currentFloodRisk
        WeatherForecastResponse forecast = fetchSevenDayForecast(district);

        // Build a WeatherDataResponse from today's values
        WeatherData saved = weatherRepository
                .findTopByDistrictOrderByRecordedAtDesc(district)
                .orElse(new WeatherData());

        return WeatherDataTransformer.toResponse(saved);
    }

    public WeatherForecastResponse fetchSevenDayForecast(String district) {

        double[] coords = getCoords(district);

        // Open-Meteo daily endpoint — no API key needed
        String url = String.format(
                "%s?latitude=%s&longitude=%s&daily=precipitation_sum,temperature_2m_max,temperature_2m_min,windspeed_10m_max,precipitation_probability_max&timezone=auto&forecast_days=7",
                OPEN_METEO_URL,
                coords[0],
                coords[1]
        );
        List<Map<String, Object>> dailyForecast = new ArrayList<>();
        double currentRainfallMm = 0.0;
        String currentFloodRisk  = "LOW";

        try {
            ResponseEntity<Map> response =
                    restTemplate.getForEntity(url, Map.class);

            Map<String, Object> body = response.getBody();

            // Open-Meteo returns a "daily" object with parallel arrays
            Map<String, Object> daily =
                    (Map<String, Object>) body.get("daily");

            List<String>  dates    = (List<String>)  daily.get("time");
            List<?>       precip   = (List<?>)        daily.get("precipitation_sum");
            List<?>       tempMax  = (List<?>)        daily.get("temperature_2m_max");
            List<?>       tempMin  = (List<?>)        daily.get("temperature_2m_min");
            List<?>       wind     = (List<?>)        daily.get("windspeed_10m_max");
            List<?>       rainProb = (List<?>)        daily.get("precipitation_probability_max");

            // Generate day labels dynamically from actual dates
            String[] dayNames = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};

            for (int i = 0; i < dates.size(); i++) {

                double rain    = getValue(precip,   i);
                double maxTemp = getValue(tempMax,  i);
                double minTemp = getValue(tempMin,  i);
                int    prob    = getIntValue(rainProb, i);

                rain           = Math.round(rain * 10.0) / 10.0;
                double avgTemp = Math.round(((maxTemp + minTemp) / 2) * 10.0) / 10.0;
                String risk    = determineFloodRisk(rain);

                // Dynamic day label from actual date
                LocalDate dateObj = LocalDate.parse(dates.get(i));
                String dayLabel   = i == 0
                        ? "Today"
                        : dayNames[dateObj.getDayOfWeek().getValue() % 7];

                Map<String, Object> entry = new LinkedHashMap<>();
                entry.put("day",             dayLabel);
                entry.put("date",            dates.get(i));
                entry.put("rainfallMm",      rain);
                entry.put("riskLevel",       risk);
                entry.put("tempMaxC",        Math.round(maxTemp * 10.0) / 10.0);
                entry.put("tempMinC",        Math.round(minTemp * 10.0) / 10.0);
                entry.put("tempAvgC",        avgTemp);
                entry.put("windKmh",         getValue(wind, i));
                entry.put("rainProbability", prob);
                dailyForecast.add(entry);

                if (i == 0) {
                    currentRainfallMm = rain;
                    currentFloodRisk  = risk;
                }
            }

            // Save today's values to your DB
            WeatherData todayRecord = weatherRepository
                    .findTopByDistrictOrderByRecordedAtDesc(district)
                    .orElse(new WeatherData());
            todayRecord.setDistrict(district);
            todayRecord.setRainfallMm(currentRainfallMm);
            todayRecord.setFloodRisk(currentFloodRisk);
            weatherRepository.save(todayRecord);

            System.out.println("Open-Meteo forecast fetched for "
                    + district + " — today: " + currentRainfallMm
                    + "mm, risk: " + currentFloodRisk);

        } catch (Exception e) {
            System.out.println("Open-Meteo failed for "
                    + district + ": " + e.getMessage());
        }

        WeatherForecastResponse result = new WeatherForecastResponse();
        result.setDistrict(district);
        result.setCurrentRainfallMm(currentRainfallMm);
        result.setCurrentFloodRisk(currentFloodRisk);
        result.setDailyForecast(dailyForecast);
        result.setFetchedAt(LocalDateTime.now());
        return result;
    }
    private double getValue(List<?> list, int index) {
        if (list == null || index >= list.size() || list.get(index) == null)
            return 0.0;
        return ((Number) list.get(index)).doubleValue();
    }

    private int getIntValue(List<?> list, int index) {
        if (list == null || index >= list.size() || list.get(index) == null)
            return 0;
        return ((Number) list.get(index)).intValue();
    }
}