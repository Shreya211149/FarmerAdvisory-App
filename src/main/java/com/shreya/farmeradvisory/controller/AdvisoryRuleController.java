package com.shreya.farmeradvisory.controller;

import com.shreya.farmeradvisory.dto.request.AdvisoryRuleRequest;
import com.shreya.farmeradvisory.dto.response.AdvisoryRuleResponse;
import com.shreya.farmeradvisory.service.AdvisoryRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
@RequiredArgsConstructor
public class AdvisoryRuleController {

    private final AdvisoryRuleService ruleService;

    @GetMapping                          // ← GET /api/rules
    public List<AdvisoryRuleResponse> getAllRules() {
        return ruleService.getAllRules();
    }

    @PostMapping
    public AdvisoryRuleResponse createRule(@RequestBody AdvisoryRuleRequest request) {
        return ruleService.createRule(request);
    }

    @PutMapping("/{id}/toggle")
    public AdvisoryRuleResponse toggleRule(@PathVariable Long id) {
        return ruleService.toggleRule(id);
    }

    @DeleteMapping("/{id}")
    public void deleteRule(@PathVariable Long id) {
        ruleService.deleteRule(id);
    }
}
