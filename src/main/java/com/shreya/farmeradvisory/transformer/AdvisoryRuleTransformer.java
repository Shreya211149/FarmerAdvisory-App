package com.shreya.farmeradvisory.transformer;

import com.shreya.farmeradvisory.dto.request.AdvisoryRuleRequest;
import com.shreya.farmeradvisory.dto.response.AdvisoryRuleResponse;
import com.shreya.farmeradvisory.models.AdvisoryRule;

public class AdvisoryRuleTransformer {
    public static AdvisoryRule toEntity(AdvisoryRuleRequest request) {
        return AdvisoryRule.builder()
                .conditionField(request.getConditionField())
                .thresholdValue(request.getThresholdValue())
                .operator(request.getOperator())
                .advisoryMessage(request.getAdvisoryMessage())
                .severity(request.getSeverity())
                .active(true)
                .build();
    }

    public static AdvisoryRuleResponse toResponse(AdvisoryRule rule) {
        return AdvisoryRuleResponse.builder()
                .id(rule.getId())
                .conditionField(rule.getConditionField())
                .operator(rule.getOperator())
                .thresholdValue(rule.getThresholdValue())
                .advisoryMessage(rule.getAdvisoryMessage())
                .severity(rule.getSeverity())
                .active(rule.getActive())
                .build();
    }
}
