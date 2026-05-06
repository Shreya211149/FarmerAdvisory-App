package com.shreya.farmeradvisory.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FarmerGroupRequest {
    private String name;
    private String district;
}
