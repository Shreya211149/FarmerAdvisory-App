package com.shreya.farmeradvisory.repo;

import com.shreya.farmeradvisory.models.Alert;
import com.shreya.farmeradvisory.models.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findBySeverity(String severity);
    List<Alert> findByDistrict(String district);
    List<Alert> findByDistrictOrderBySentAtDesc(String district);

    List<Alert> findByFarmer(Farmer farmer);
    @Query("SELECT a FROM Alert a WHERE a.sentAt >= :from ORDER BY a.sentAt ASC")
    List<Alert> findAlertsAfter(@Param("from") LocalDateTime from);
}