package com.shreya.farmeradvisory.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AlertResponse {
    private Long id;
    private String message;
    private String severity;
    private String district;
    private LocalDateTime sentAt;
    private String farmerName;   // flattened
    private String farmerPhone;
}
