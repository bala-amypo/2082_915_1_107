package com.example.demo.controller;

import com.example.demo.model.ClaimRule;
import com.example.demo.service.ClaimRuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/rules")
@Tag(name = "Claim Rules")
public class ClaimRuleController {
    
    private final ClaimRuleService ruleService;
    
    public ClaimRuleController(ClaimRuleService ruleService) {
        this.ruleService = ruleService;
    }
    
    @PostMapping
    @Operation(summary = "Add claim rule")
    public ResponseEntity<ClaimRule> addRule(@RequestBody ClaimRule rule) {
        return ResponseEntity.ok(ruleService.addRule(rule));
    }
    
    @GetMapping
    @Operation(summary = "Get all claim rules")
    public ResponseEntity<List<ClaimRule>> getAllRules() {
        return ResponseEntity.ok(ruleService.getAllRules());
    }
}