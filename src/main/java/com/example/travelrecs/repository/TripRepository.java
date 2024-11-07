package com.example.travelrecs.repository;

import com.example.travelrecs.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
//    boolean existsByTripName(String tripName);
}