package com.shreya.farmeradvisory.controller;

import com.shreya.farmeradvisory.dto.response.AlertResponse;
import com.shreya.farmeradvisory.service.AlertService;
import com.shreya.farmeradvisory.service.EvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/alerts")
@RequiredArgsConstructor
public class AlertController {

    private final AlertService alertService;
    private final EvaluationService evaluationService;

    @GetMapping("/district/{district}")
    public List<AlertResponse> getByDistrict(@PathVariable String district) {
        return alertService.getAlertsByDistrict(district);
    }

    @GetMapping("/history/14days")          // ← add this
    public Map<String, Object> getAlertHistory() {
        return alertService.getAlertHistoryLast14Days();
    }

    @GetMapping
    public List<AlertResponse> getAllAlerts() {
        return alertService.getAllAlerts();
    }

    @PostMapping("/evaluate/{district}")
    public List<AlertResponse> evaluateAlerts(@PathVariable String district) {
        return evaluationService.evaluateAndSendAlerts(district);
    }
}