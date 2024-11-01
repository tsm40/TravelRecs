package com.example.travelrecs.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class State {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long stateId;
    String stateName;
}
