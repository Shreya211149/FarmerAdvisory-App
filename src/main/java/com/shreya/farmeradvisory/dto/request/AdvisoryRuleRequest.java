package com.shreya.farmeradvisory.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdvisoryRuleRequest {
    private String conditionField;
    private String operator;
    private Double thresholdValue;
    private String advisoryMessage;
    private String severity;
}