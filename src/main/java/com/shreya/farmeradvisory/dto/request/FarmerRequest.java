package com.shreya.farmeradvisory.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FarmerRequest {
    private String name;
    private String phone;
    private String district;
    private Double farmSizeAcres;
    private Double latitude;
    private Double longitude;
    private Long groupId;
    private String username;
    private String password;
}
