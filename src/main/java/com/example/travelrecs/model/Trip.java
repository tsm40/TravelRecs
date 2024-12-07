package com.example.travelrecs.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate; // Date format without time
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "trip_id")
    private Long tripId;  // Primary key for Trip entity

    private int totalBudget;  // Planned budget for the trip

    private int currBudget;  // Current budget for the trip with places considered

    private String startDate;  // Start date of the trip

    private String endDate;  // End date of the trip


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")  // Foreign key referencing User entity
    @JsonBackReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Day> days = new ArrayList<>();

    public void addDay(Day day) {
        days.add(day);
        day.setTrip(this);
    }

    public void removeDay(Day day) {
        days.remove(day);
        day.setTrip(null);
    }
}
