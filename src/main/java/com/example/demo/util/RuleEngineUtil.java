package com.example.demo.util;

import com.example.demo.model.ClaimRule;
import com.example.demo.model.DamageClaim;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

@Component
public class RuleEngineUtil {
    
    public double evaluateRules(DamageClaim claim, List<ClaimRule> rules) {
        double totalScore = 0.0;
        Set<ClaimRule> appliedRules = new HashSet<>();
        
        for (ClaimRule rule : rules) {
            if (evaluateRule(claim, rule)) {
                totalScore += rule.getWeight();
                appliedRules.add(rule);
            }
        }
        
        claim.setAppliedRules(appliedRules);
        return Math.min(totalScore, 1.0);
    }
    
    public static double computeScore(String description, List<ClaimRule> rules) {
        double totalScore = 0.0;
        
        for (ClaimRule rule : rules) {
            if (evaluateRuleForDescription(description, rule)) {
                totalScore += rule.getWeight();
            }
        }
        
        return Math.min(totalScore, 1.0);
    }
    
    private boolean evaluateRule(DamageClaim claim, ClaimRule rule) {
        String condition = rule.getConditionExpression();
        
        if ("always".equals(condition)) {
            return true;
        }
        
        if (condition.startsWith("description_contains:")) {
            String keyword = condition.substring("description_contains:".length());
            return claim.getClaimDescription() != null && 
                   claim.getClaimDescription().toLowerCase().contains(keyword.toLowerCase());
        }
        
        return false;
    }
    
    private static boolean evaluateRuleForDescription(String description, ClaimRule rule) {
        String condition = rule.getConditionExpression();
        
        if ("always".equals(condition)) {
            return true;
        }
        
        if (condition.startsWith("description_contains:")) {
            String keyword = condition.substring("description_contains:".length());
            return description != null && 
                   description.toLowerCase().contains(keyword.toLowerCase());
        }
        
        return false;
    }
}