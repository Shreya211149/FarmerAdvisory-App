package com.shreya.farmeradvisory.service;

import com.shreya.farmeradvisory.dto.request.AlertRequest;
import com.shreya.farmeradvisory.dto.response.AlertResponse;
import com.shreya.farmeradvisory.exception.FarmerNotFoundException;
import com.shreya.farmeradvisory.models.Alert;
import com.shreya.farmeradvisory.models.Farmer;
import com.shreya.farmeradvisory.repo.AlertRepository;
import com.shreya.farmeradvisory.repo.FarmerRepository;
import com.shreya.farmeradvisory.transformer.AlertTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AlertService {

    private final AlertRepository alertRepository;
    private final FarmerRepository farmerRepository;


    public List<AlertResponse> getAllAlerts() {
        return alertRepository.findAll()
                .stream()
                .map(AlertTransformer::toResponse)
                .toList();
    }

    public List<AlertResponse> getAlertsByDistrict(String district) {
        return alertRepository.findByDistrictOrderBySentAtDesc(district)
                .stream()
                .map(AlertTransformer::toResponse)
                .toList();
    }

    public AlertResponse createAlert(AlertRequest request) {
        Farmer farmer = farmerRepository.findById(request.getFarmerId())
                .orElseThrow(() ->
                        new FarmerNotFoundException(
                                "Farmer not found: " + request.getFarmerId()));
        Alert alert = AlertTransformer.toEntity(request, farmer);
        return AlertTransformer.toResponse(alertRepository.save(alert));
    }
    public Map<String, Object> getAlertHistoryLast14Days() {

        LocalDateTime from = LocalDateTime.now().minusDays(14);
        List<Alert> recent = alertRepository.findAlertsAfter(from);

        List<String>  labels   = new ArrayList<>();
        List<Integer> critical = new ArrayList<>();
        List<Integer> warning  = new ArrayList<>();
        List<Integer> info     = new ArrayList<>();

        for (int i = 13; i >= 0; i--) {
            LocalDate date = LocalDate.now().minusDays(i);
            labels.add(date.format(DateTimeFormatter.ofPattern("d MMM")));

            long c = recent.stream().filter(a ->
                    a.getSentAt() != null &&
                            a.getSentAt().toLocalDate().equals(date) &&
                            "CRITICAL".equals(a.getSeverity())).count();

            long w = recent.stream().filter(a ->
                    a.getSentAt() != null &&
                            a.getSentAt().toLocalDate().equals(date) &&
                            "WARNING".equals(a.getSeverity())).count();

            long inf = recent.stream().filter(a ->
                    a.getSentAt() != null &&
                            a.getSentAt().toLocalDate().equals(date) &&
                            "INFO".equals(a.getSeverity())).count();

            critical.add((int) c);
            warning.add((int) w);
            info.add((int) inf);
        }

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("labels",   labels);
        result.put("critical", critical);
        result.put("warning",  warning);
        result.put("info",     info);
        return result;
    }
}