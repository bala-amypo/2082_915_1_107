package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DamageClaimService;
import com.example.demo.util.RuleEngineUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DamageClaimServiceImpl implements DamageClaimService {

    private final ParcelRepository parcelRepository;
    private final DamageClaimRepository damageClaimRepository;
    private final ClaimRuleRepository claimRuleRepository;

    // REQUIRED constructor order
    public DamageClaimServiceImpl(ParcelRepository parcelRepository,
                                  DamageClaimRepository damageClaimRepository,
                                  ClaimRuleRepository claimRuleRepository) {
        this.parcelRepository = parcelRepository;
        this.damageClaimRepository = damageClaimRepository;
        this.claimRuleRepository = claimRuleRepository;
    }

    @Override
    public DamageClaim fileClaim(Long parcelId, DamageClaim claim) {
        Parcel parcel = parcelRepository.findById(parcelId)
                .orElseThrow(() -> new RuntimeException("parcel not found"));

        claim.setParcel(parcel);
        claim.setStatus("PENDING");
        return damageClaimRepository.save(claim);
    }

    @Override
    public DamageClaim evaluateClaim(Long claimId) {
        DamageClaim claim = damageClaimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("claim not found"));

        List<ClaimRule> rules = claimRuleRepository.findAll();
        double score = RuleEngineUtil.computeScore(
                claim.getClaimDescription(), rules
        );

        claim.setScore(score);
        claim.setAppliedRules(rules);
        claim.setStatus(score >= 50 ? "APPROVED" : "REJECTED");

        return damageClaimRepository.save(claim);
    }

    @Override
    public DamageClaim getClaim(Long claimId) {
        return damageClaimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("claim not found"));
    }
}
