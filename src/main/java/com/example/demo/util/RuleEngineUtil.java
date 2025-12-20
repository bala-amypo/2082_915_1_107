package com.example.demo.util;

import com.example.demo.model.ClaimRule;
import java.util.List;

public class RuleEngineUtil {

    // Prevent object creation
    private RuleEngineUtil() {
    }

    /**
     * Computes claim score based on matching rule conditions
     */
    public static double computeScore(String description, List<ClaimRule> rules) {

        if (description == null || rules == null || rules.isEmpty()) {
            return 0;
        }

        double score = 0;

        for (ClaimRule rule : rules) {
            if (rule.getConditionExpression() != null &&
                description.toLowerCase()
                        .contains(rule.getConditionExpression().toLowerCase())) {

                score += rule.getWeight();
            }
        }

        return score;
    }
}
