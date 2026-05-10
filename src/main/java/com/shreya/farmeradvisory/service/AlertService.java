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

import java.util.List;

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
        return alertRepository.findByDistrict(district)
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
}