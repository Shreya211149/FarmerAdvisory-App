package com.shreya.farmeradvisory.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "weather_data")
@Getter @Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class WeatherData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "district")
    private String district;

    @Column(name = "rainfall_mm")
    private Double rainfallMm;

    @Column(name = "flood_risk")
    private String floodRisk;

    @Builder.Default
    private LocalDateTime recordedAt = LocalDateTime.now();
}
