package com.shreya.farmeradvisory.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AdvisoryRuleResponse {
    private Long id;
    private String conditionField;
    private String operator;
    private Double thresholdValue;
    private String advisoryMessage;
    private String severity;
    private Boolean active;
}
