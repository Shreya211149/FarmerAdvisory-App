package com.shreya.farmeradvisory.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class FarmerGroupResponse {
    private Long id;
    private String name;
    private String district;
    private LocalDateTime createdAt;
}
