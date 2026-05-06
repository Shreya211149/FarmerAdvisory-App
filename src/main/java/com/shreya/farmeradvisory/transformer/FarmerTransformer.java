package com.shreya.farmeradvisory.transformer;

import com.shreya.farmeradvisory.dto.request.FarmerRequest;
import com.shreya.farmeradvisory.dto.response.FarmerResponse;
import com.shreya.farmeradvisory.models.Farmer;
import com.shreya.farmeradvisory.models.FarmerGroup;
import org.springframework.security.crypto.password.PasswordEncoder;

public class FarmerTransformer {
    public static Farmer toEntity(FarmerRequest request, FarmerGroup group, PasswordEncoder passwordEncoder) {

        return  Farmer.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .district(request.getDistrict())
                .farmSizeAcres(request.getFarmSizeAcres())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .group(group).build();
    }
    public static FarmerResponse toResponse(Farmer farmer) {
        return FarmerResponse.builder()
                .id(farmer.getId())
                .name(farmer.getName())
                .phone(farmer.getPhone())
                .district(farmer.getDistrict())
                .farmSizeAcres(farmer.getFarmSizeAcres())
                .latitude(farmer.getLatitude())
                .longitude(farmer.getLongitude())
                .groupName(farmer.getGroup() != null ? farmer.getGroup().getName() : null)
                .groupDistrict(farmer.getGroup() != null ? farmer.getGroup().getDistrict() : null)
                .build();
    }
}
