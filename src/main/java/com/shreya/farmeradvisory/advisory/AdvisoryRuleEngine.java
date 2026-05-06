package com.shreya.farmeradvisory.advisory;

import com.shreya.farmeradvisory.dto.request.AlertRequest;
import com.shreya.farmeradvisory.models.AdvisoryRule;
import com.shreya.farmeradvisory.models.Farmer;
import com.shreya.farmeradvisory.repo.AdvisoryRuleRepository;
import com.shreya.farmeradvisory.service.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvisoryRuleEngine {

    private final AdvisoryRuleRepository ruleRepository;
    private final AlertService alertService;

    public void evaluate(
            Farmer farmer,
            double rainfallMm,
            String floodRisk
    ) {

        List<AdvisoryRule> rules = ruleRepository.findByActiveTrue();

        for (AdvisoryRule rule : rules) {

            if (matches(rule, rainfallMm, floodRisk)) {

                AlertRequest request = new AlertRequest();
                request.setMessage(rule.getAdvisoryMessage());
                request.setSeverity(rule.getSeverity());
                request.setDistrict(farmer.getDistrict());
                request.setFarmerId(farmer.getId());

                alertService.createAlert(request);
            }
        }
    }

    private boolean matches(
            AdvisoryRule rule,
            double rainfallMm,
            String floodRisk
    ) {
        return switch (rule.getConditionField()) {
            case "rainfall_mm" ->
                    compare(rainfallMm, rule);
            case "flood_risk" ->
                    floodRisk.equalsIgnoreCase(
                            String.valueOf(rule.getThresholdValue())
                    );
            default -> false;
        };
    }

    private boolean compare(double value, AdvisoryRule rule) {
        return switch (rule.getOperator()) {
            case "GREATER_THAN" -> value > rule.getThresholdValue();
            case "LESS_THAN" -> value < rule.getThresholdValue();
            case "EQUALS" -> value == rule.getThresholdValue();
            default -> false;
        };
    }
}