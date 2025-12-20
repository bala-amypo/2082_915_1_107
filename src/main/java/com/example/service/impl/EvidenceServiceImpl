package com.example.demo.service.impl;

import com.example.demo.model.Evidence;
import com.example.demo.model.DamageClaim;
import com.example.demo.repository.EvidenceRepository;
import com.example.demo.repository.DamageClaimRepository;

import java.util.List;

public class EvidenceServiceImpl {

    private final EvidenceRepository evidenceRepository;
    private final DamageClaimRepository claimRepository;

    // REQUIRED constructor
    public EvidenceServiceImpl(
            EvidenceRepository evidenceRepository,
            DamageClaimRepository claimRepository
    ) {
        this.evidenceRepository = evidenceRepository;
        this.claimRepository = claimRepository;
    }

    public Evidence uploadEvidence(Long claimId, Evidence evidence) {

        DamageClaim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found"));

        evidence.setClaim(claim);
        return evidenceRepository.save(evidence);
    }

    public List<Evidence> getEvidenceForClaim(Long claimId) {
        return evidenceRepository.findByClaim_Id(claimId);
    }
}
