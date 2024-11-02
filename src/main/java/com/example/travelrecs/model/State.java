package com.example.travelrecs.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long stateId;  // Primary key for State entity

    private String stateName;  // Name of the state

    @ManyToOne
    @JoinColumn(name = "countryId") // Foreign key column referencing Country entity
    private Country country;
}
