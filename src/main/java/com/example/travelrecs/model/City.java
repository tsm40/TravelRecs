package com.example.travelrecs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

//Data: mark the data as data class to support auto getter and setter method
@Data
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cityId;

    private String cityName;

    @ManyToOne
    @JoinColumn(name = "stateId") // This is the foreign key column
    private State state;
}
