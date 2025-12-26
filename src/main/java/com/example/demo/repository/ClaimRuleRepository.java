package com.example.demo.repository;

import com.example.demo.model.ClaimRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClaimRuleRepository extends JpaRepository<ClaimRule, Long> {
    List<ClaimRule> findAll();
}