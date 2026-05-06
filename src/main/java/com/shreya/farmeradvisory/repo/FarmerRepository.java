package com.shreya.farmeradvisory.repo;

import com.shreya.farmeradvisory.models.Farmer;
import com.shreya.farmeradvisory.models.FarmerGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Long> {
    List<Farmer> findByDistrict(String district);
    List<Farmer> findByGroup(FarmerGroup group);
    Optional<Farmer> findByUsername(String username);
}
