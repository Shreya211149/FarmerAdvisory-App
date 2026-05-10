package com.shreya.farmeradvisory.service;

import com.shreya.farmeradvisory.dto.request.AdvisoryRuleRequest;
import com.shreya.farmeradvisory.dto.response.AdvisoryRuleResponse;
import com.shreya.farmeradvisory.models.AdvisoryRule;
import com.shreya.farmeradvisory.repo.AdvisoryRuleRepository;
import com.shreya.farmeradvisory.transformer.AdvisoryRuleTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvisoryRuleService {

    private final AdvisoryRuleRepository ruleRepository;

    // Called by AdvisoryRuleController — GET /api/rules
    public List<AdvisoryRuleResponse> getAllRules() {
        return ruleRepository.findAll()
                .stream()
                .map(AdvisoryRuleTransformer::toResponse)
                .toList();
    }

    // Called by AdvisoryRuleController — GET /api/rules/active
    public List<AdvisoryRuleResponse> getActiveRules() {
        return ruleRepository.findByActiveTrue()
                .stream()
                .map(AdvisoryRuleTransformer::toResponse)
                .toList();
    }

    // Called by AdvisoryRuleController — POST /api/rules
    public AdvisoryRuleResponse createRule(AdvisoryRuleRequest request) {
        AdvisoryRule rule = AdvisoryRuleTransformer.toEntity(request);
        return AdvisoryRuleTransformer.toResponse(ruleRepository.save(rule));
    }

    // Called by AdvisoryRuleController — PUT /api/rules/{id}/toggle
    public AdvisoryRuleResponse toggleRule(Long id) {
        AdvisoryRule rule = ruleRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Rule not found: " + id));
        rule.setActive(!rule.getActive());
        return AdvisoryRuleTransformer.toResponse(ruleRepository.save(rule));
    }

    // Called by AdvisoryRuleController — DELETE /api/rules/{id}
    public void deleteRule(Long id) {
        if (!ruleRepository.existsById(id)) {
            throw new RuntimeException("Rule not found: " + id);
        }
        ruleRepository.deleteById(id);
    }
}