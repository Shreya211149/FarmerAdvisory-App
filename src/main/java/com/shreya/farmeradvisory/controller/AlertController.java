package com.shreya.farmeradvisory.controller;

import com.shreya.farmeradvisory.dto.response.AlertResponse;
import com.shreya.farmeradvisory.service.AlertService;
import com.shreya.farmeradvisory.service.EvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@RequiredArgsConstructor
public class AlertController {

    private final AlertService alertService;
    private final EvaluationService evaluationService;

    @GetMapping("/district/{district}")          // ← must match exactly
    public List<AlertResponse> getByDistrict(@PathVariable String district) {
        return alertService.getAlertsByDistrict(district);
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