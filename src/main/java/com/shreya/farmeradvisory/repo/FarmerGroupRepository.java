package com.shreya.farmeradvisory.repo;

import com.shreya.farmeradvisory.models.FarmerGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmerGroupRepository extends JpaRepository<FarmerGroup, Long> {
    List<FarmerGroup> findByDistrict(String district);
}
