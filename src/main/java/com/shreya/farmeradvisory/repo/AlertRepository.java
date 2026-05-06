package com.shreya.farmeradvisory.repo;

import com.shreya.farmeradvisory.models.Alert;
import com.shreya.farmeradvisory.models.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findBySeverity(String severity);
    List<Alert> findByDistrict(String district);
    List<Alert> findByFarmer(Farmer farmer);
}