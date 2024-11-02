package com.example.travelrecs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Data
@Entity
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dayId;  // Primary key for Day entity

    private int index;  // Day index within the trip (e.g., Day 1, Day 2, etc.)

    @ManyToOne
    @JoinColumn(name = "placeId")  // Foreign key referencing Place entity
    private Place place;

    @ManyToOne
    @JoinColumn(name = "tripId")  // Foreign key referencing Trip entity
    private Trip trip;
}
