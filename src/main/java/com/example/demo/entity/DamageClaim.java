package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "damage_claims")
public class DamageClaim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Parcel parcel;

    private String claimDescription;

    private LocalDateTime filedAt;

    private String status = "PENDING";

    private Double score;

    @ManyToMany
    @JoinTable(
            name = "claim_applied_rules",
            joinColumns = @JoinColumn(name = "claim_id"),
            inverseJoinColumns = @JoinColumn(name = "rule_id")
    )
    private Set<ClaimRule> appliedRules = new HashSet<>();

    @OneToMany(mappedBy = "claim", cascade = CascadeType.ALL)
    private Set<Evidence> evidenceList = new HashSet<>();

    public DamageClaim() {
        this.status = "PENDING";
    }

    @PrePersist
    public void onCreate() {
        this.filedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Parcel getParcel() {
        return parcel;
    }

    public String getClaimDescription() {
        return claimDescription;
    }

    public LocalDateTime getFiledAt() {
        return filedAt;
    }

    public String getStatus() {
        return status;
    }

    public Double getScore() {
        return score;
    }

    public Set<ClaimRule> getAppliedRules() {
        return appliedRules;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setParcel(Parcel parcel) {
        this.parcel = parcel;
    }

    public void setClaimDescription(String claimDescription) {
        this.claimDescription = claimDescription;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
