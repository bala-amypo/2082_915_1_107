package com.example.demo.service.impl;

import com.example.demo.model.DamageClaim;
import com.example.demo.model.Parcel;
import com.example.demo.model.ClaimRule;
import com.example.demo.repository.DamageClaimRepository;
import com.example.demo.repository.ParcelRepository;
import com.example.demo.repository.ClaimRuleRepository;
import com.example.demo.util.RuleEngineUtil;

import java.util.List;

public class DamageClaimServiceImpl {

    private final ParcelRepository parcelRepository;
    private final DamageClaimRepository claimRepository;
    private final ClaimRuleRepository ruleRepository;

    // REQUIRED constructor (tests depend on this exact signature)
    public DamageClaimServiceImpl(
            ParcelRepository parcelRepository,
            DamageClaimRepository claimRepository,
            ClaimRuleRepository ruleRepository
    ) {
        this.parcelRepository = parcelRepository;
        this.claimRepository = claimRepository;
        this.ruleRepository = ruleRepository;
    }

    public DamageClaim fileClaim(Long parcelId, DamageClaim claim) {

        Parcel parcel = parcelRepository.findById(parcelId)
                .orElseThrow(() -> new RuntimeException("Parcel not found"));

        claim.setParcel(parcel);
        claim.setStatus("PENDING");

        return claimRepository.save(claim);
    }

    public DamageClaim evaluateClaim(Long claimId) {

        DamageClaim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found"));

        List<ClaimRule> rules = ruleRepository.findAll();

        double score = RuleEngineUtil.computeScore(
                claim.getClaimDescription(),
                rules
        );

        claim.setScore(score);

        if (score > 0) {
            claim.setStatus("APPROVED");
        } else {
            claim.setStatus("REJECTED");
        }

        return claimRepository.save(claim);
    }

    public DamageClaim getClaim(Long claimId) {
        return claimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found"));
    }
}
