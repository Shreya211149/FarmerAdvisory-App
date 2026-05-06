package com.shreya.farmeradvisory.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class FarmerResponse {
    private Long id;
    private String name;
    private String phone;
    private String district;
    private Double farmSizeAcres;
    private Double latitude;
    private Double longitude;
    private String groupName;    // flattened — no nested object
    private String groupDistrict;
}
