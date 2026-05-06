package com.shreya.farmeradvisory.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "advisory_rules")
@Getter @Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AdvisoryRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String conditionField;   // "rainfall_mm", "flood_risk"

    @Column(nullable = false)
    private String operator;         // "GREATER_THAN", "LESS_THAN", "EQUALS"

    @Column(nullable = false)
    private Double thresholdValue;   // 80.0, 50.0, 10.0

    @Column(nullable = false)
    private String advisoryMessage;  // "Harvest crop immediately"

    @Column(nullable = false)
    private String severity;         // "CRITICAL", "WARNING", "INFO"

    private Boolean active = true;   // lets you toggle rules on/off
}
