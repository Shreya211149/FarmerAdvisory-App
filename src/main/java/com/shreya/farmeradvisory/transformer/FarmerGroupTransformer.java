package com.shreya.farmeradvisory.transformer;

import com.shreya.farmeradvisory.dto.request.FarmerGroupRequest;
import com.shreya.farmeradvisory.dto.response.FarmerGroupResponse;
import com.shreya.farmeradvisory.models.FarmerGroup;

public class FarmerGroupTransformer {
    public static FarmerGroup toEntity(FarmerGroupRequest request) {
        return FarmerGroup.builder()
                .name(request.getName())
                .district(request.getDistrict())
                .build();

    }
    public static FarmerGroupResponse toResponse(FarmerGroup group) {
        return FarmerGroupResponse.builder()
                .id(group.getId())
                .name(group.getName())
                .district(group.getDistrict())
                .createdAt(group.getCreatedAt())
                .build();



    }
}
