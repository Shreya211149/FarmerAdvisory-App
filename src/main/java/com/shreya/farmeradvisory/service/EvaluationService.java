package com.shreya.farmeradvisory.service;

import com.shreya.farmeradvisory.advisory.AdvisoryRuleEngine;
import com.shreya.farmeradvisory.dto.response.AlertResponse;
import com.shreya.farmeradvisory.models.Farmer;
import com.shreya.farmeradvisory.models.WeatherData;
import com.shreya.farmeradvisory.repo.FarmerRepository;
import com.shreya.farmeradvisory.repo.WeatherDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EvaluationService {

    private final AdvisoryRuleEngine advisoryRuleEngine;
    private final AlertService alertService;
    private final FarmerRepository farmerRepository;
    private final WeatherDataRepository weatherRepository;

    public List<AlertResponse> evaluateAndSendAlerts(String district) {

        WeatherData weather = weatherRepository
                .findTopByDistrictOrderByRecordedAtDesc(district)
                .orElseThrow(() ->
                        new RuntimeException("No weather data found for: " + district));

        List<Farmer> farmers = farmerRepository.findByDistrict(district);

        if (farmers.isEmpty()) {
            throw new RuntimeException("No farmers found in district: " + district);
        }

        for (Farmer farmer : farmers) {
            advisoryRuleEngine.evaluate(
                    farmer,
                    weather.getRainfallMm(),
                    weather.getFloodRisk()
            );
        }

        return alertService.getAlertsByDistrict(district);
    }
}