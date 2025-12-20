package com.example.demo.service.impl;

import com.example.demo.model.ClaimRule;
import com.example.demo.repository.ClaimRuleRepository;

import java.util.List;

public class ClaimRuleServiceImpl {

    private final ClaimRuleRepository ruleRepository;

    // REQUIRED constructor
    public ClaimRuleServiceImpl(ClaimRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    public ClaimRule addRule(ClaimRule rule) {

        if (rule.getWeight() < 0) {
            throw new RuntimeException("Weight must be >= 0");
        }

        return ruleRepository.save(rule);
    }

    public List<ClaimRule> getAllRules() {
        return ruleRepository.findAll();
    }
}
