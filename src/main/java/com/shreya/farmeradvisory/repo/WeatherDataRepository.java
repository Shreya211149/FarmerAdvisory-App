package com.shreya.farmeradvisory.repo;

import com.shreya.farmeradvisory.models.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {
    List<WeatherData> findByDistrict(String district);
    void deleteByRecordedAtBefore(LocalDateTime dateTime);
    Optional<WeatherData> findTopByDistrictOrderByRecordedAtDesc(String district);
}
