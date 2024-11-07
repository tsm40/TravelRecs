package com.example.travelrecs.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

//Data: mark the data as data class to support auto getter and setter method
@Getter
@Setter
@Entity
@Table(name = "place")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "place_id")
    private Long placeId;  // Primary key for Place entity

    private double latitude;  // Exact location - Latitude
    private double longitude; // Exact location - Longitude

    private String name;  // Name of the place

    private String description;  // Detailed description of the place

    @Enumerated(EnumType.STRING)
    private Category category;  // Category (Attractions, Dining, Accommodations)

    private float rating;  // Rating with two decimal precision

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)  // Foreign key referencing City entity
    @JsonBackReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private City city;

    private int upperCost;  // Upper cost estimate for this place
    private int lowerCost;  // Lower cost estimate for this place

    private float duration;  // Estimated duration to spend at this place (single decimal precision)

    private String photoURL;  // URL to a photo of the place

    @ManyToMany(mappedBy = "places")
    @JsonBackReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Day> days = new HashSet<>();

    // Enum for category
    public enum Category {
        ATTRACTIONS,
        DINING,
        ACCOMMODATIONS;

        // Static method to handle variations
        public static Category fromString(String category) {
            switch (category.toLowerCase()) {
                case "attractions":
                    return ATTRACTIONS;
                case "dining":
                    return DINING;
                case "accommodations":
                    return ACCOMMODATIONS;
                default:
                    throw new IllegalArgumentException("Unknown category: " + category);
            }
        }
    }
}