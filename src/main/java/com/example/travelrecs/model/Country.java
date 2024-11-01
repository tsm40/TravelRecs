package com.example.travelrecs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;

import lombok.Data;

@Data
@Entity
public class Country {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long countryId;
    String countryName;
}
