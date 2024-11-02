package com.example.travelrecs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

//Data: mark the data as data class to support auto getter and setter method
@Data
@Entity
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long placeId;  // Primary key for Place entity

    private double latitude;  // Exact location - Latitude
    private double longitude; // Exact location - Longitude

    private String name;  // Name of the place

    private String description;  // Detailed description of the place

    @Enumerated(EnumType.STRING)
    private Category category;  // Category (Attractions, Dining, Accommodations)

    private float rating;  // Rating with two decimal precision

    @ManyToOne
    @JoinColumn(name = "cityId")  // Foreign key referencing City entity
    private City city;

    private int upperCost;  // Upper cost estimate for this place
    private int lowerCost;  // Lower cost estimate for this place

    private float duration;  // Estimated duration to spend at this place (single decimal precision)

    private String photoURL;  // URL to a photo of the place

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