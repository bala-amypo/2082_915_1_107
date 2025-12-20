package com.example.demo.util;

import com.example.demo.model.ClaimRule;

import java.util.List;

public class RuleEngineUtil {

    private RuleEngineUtil() {
        // utility class
    }

    /**
     * Computes normalized score between 0.0 and 1.0
     */
    public static double computeScore(String description, List<ClaimRule> rules) {

        if (description == null || rules == null || rules.isEmpty()) {
            return 0.0;
        }

        double totalWeight = 0.0;
        double matchedWeight = 0.0;

        for (ClaimRule rule : rules) {

            if (rule == null || rule.getConditionExpression() == null) {
                continue;
            }

            double weight = rule.getWeight() == null ? 0.0 : rule.getWeight();
            totalWeight += weight;

            String expr = rule.getConditionExpression().toLowerCase();

            // ALWAYS rule
            if (expr.equals("always")) {
                matchedWeight += weight;
            }

            // KEYWORD rule: description_contains:KEYWORD
            else if (expr.startsWith("description_contains:")) {
                String keyword = expr.substring("description_contains:".length());
                if (description.toLowerCase().contains(keyword)) {
                    matchedWeight += weight;
                }
            }

            // invalid expressions → ignored
        }

        if (totalWeight == 0.0) {
            return 0.0;
        }

        double score = matchedWeight / totalWeight;

        // normalize
        if (score > 1.0) {
            return 1.0;
        }
        if (score < 0.0) {
            return 0.0;
        }

        return score;
    }
}
