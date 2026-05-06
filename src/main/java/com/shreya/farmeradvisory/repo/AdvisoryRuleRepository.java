package com.shreya.farmeradvisory.repo;

import com.shreya.farmeradvisory.models.AdvisoryRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvisoryRuleRepository extends JpaRepository<AdvisoryRule, Long> {
    List<AdvisoryRule> findByActiveTrue();
}
