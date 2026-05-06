package com.shreya.farmeradvisory.transformer;

import com.shreya.farmeradvisory.dto.request.AlertRequest;
import com.shreya.farmeradvisory.dto.response.AlertResponse;
import com.shreya.farmeradvisory.models.Alert;
import com.shreya.farmeradvisory.models.Farmer;

public class AlertTransformer {
    public static Alert toEntity(AlertRequest request, Farmer farmer) {

        return Alert.builder()
                .message(request.getMessage())
                .severity(request.getSeverity())
                .district(request.getDistrict())
                .farmer(farmer)
                .build();
    }
    public static AlertResponse toResponse(Alert alert) {
        return AlertResponse.builder()
                .id(alert.getId())
                .message(alert.getMessage())
                .severity(alert.getSeverity())
                .district(alert.getDistrict())
                .sentAt(alert.getSentAt())
                .farmerName(alert.getFarmer() != null ? alert.getFarmer().getName() : null)
                .farmerPhone(alert.getFarmer() != null ? alert.getFarmer().getPhone() : null)
                .build();
    }
}
