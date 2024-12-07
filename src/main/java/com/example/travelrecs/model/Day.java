package com.example.travelrecs.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "\"day\"") // Escapes the table name in H2
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "day_id")
    private Long dayId;  // Primary key for Day entity

    @Column(name = "day_number", nullable = false)
    private int dayNumber;  // Day index within the trip (e.g., Day 1, Day 2, etc.)

    @ManyToMany
    @JoinTable(
            name = "day_places",
            joinColumns = @JoinColumn(name = "day_id"),
            inverseJoinColumns = @JoinColumn(name = "place_id")
    )
    @JsonManagedReference
    private Set<Place> places = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id")  // Foreign key referencing Trip entity
    @JsonBackReference
    private Trip trip;

    public void addPlace(Place place) {
        places.add(place);
        place.getDays().add(this);
    }

    public void removePlace(Place place) {
        places.remove(place);
        place.getDays().remove(this);
    }
}
