package com.shreya.farmeradvisory.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "farmer_groups")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class FarmerGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String district;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
