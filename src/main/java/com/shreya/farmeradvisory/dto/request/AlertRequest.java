package com.shreya.farmeradvisory.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AlertRequest {
    private String message;
    private String severity;
    private String district;
    private Long farmerId;
}