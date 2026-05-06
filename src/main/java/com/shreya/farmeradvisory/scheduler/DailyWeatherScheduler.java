package com.shreya.farmeradvisory.scheduler;

import com.shreya.farmeradvisory.advisory.AdvisoryRuleEngine;
import com.shreya.farmeradvisory.dto.response.WeatherDataResponse;
import com.shreya.farmeradvisory.models.Farmer;
import com.shreya.farmeradvisory.repo.FarmerRepository;
import com.shreya.farmeradvisory.service.WeatherApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DailyWeatherScheduler {

    private final FarmerRepository farmerRepository;
    private final WeatherApiService weatherApiService;
    private final AdvisoryRuleEngine advisoryRuleEngine;

    @Scheduled(cron = "0 0 6 * * *")
    public void runDailyWeatherCheck() {

        List<Farmer> farmers = farmerRepository.findAll();

        if (farmers.isEmpty()) {
            System.out.println("No farmers registered — skipping weather check.");
            return;
        }

        // Group farmers by district — one API call per district, not per farmer
        Map<String, List<Farmer>> farmersByDistrict = farmers.stream()
                .collect(Collectors.groupingBy(Farmer::getDistrict));

        for (Map.Entry<String, List<Farmer>> entry : farmersByDistrict.entrySet()) {

            String district = entry.getKey();
            List<Farmer> districtFarmers = entry.getValue();

            try {
                // One API call for the entire district
                WeatherDataResponse weather =
                        weatherApiService.fetchAndSaveWeather(district);

                System.out.println("Weather fetched for district: " + district
                        + " | Rainfall: " + weather.getRainfallMm() + "mm"
                        + " | Risk: " + weather.getFloodRisk());

                // Evaluate rules for every farmer in that district
                for (Farmer farmer : districtFarmers) {
                    advisoryRuleEngine.evaluate(
                            farmer,
                            weather.getRainfallMm(),
                            weather.getFloodRisk()
                    );
                }

            } catch (Exception e) {
                // One district failing does not stop the rest
                System.out.println("Weather check failed for district: "
                        + district + " — " + e.getMessage());
            }
        }
    }
}