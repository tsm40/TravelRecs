package com.example.travelrecs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate; // Date format without time
import java.util.List;

@Data
@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tripId;  // Primary key for Trip entity

    private int totalBudget;  // Planned budget for the trip

    private int currBudget;  // Current budget for the trip with places considered

    private LocalDate startDate;  // Start date of the trip

    private LocalDate endDate;  // End date of the trip

    @ManyToOne
    @JoinColumn(name = "userId")  // Foreign key referencing User entity
    private User user;
}
