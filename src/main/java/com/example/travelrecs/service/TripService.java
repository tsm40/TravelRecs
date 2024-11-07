package com.example.travelrecs.service;

import com.example.travelrecs.dto.TripDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TripService {
    TripDTO createTrip(TripDTO tripDTO);
    Page<TripDTO> getAllTrips(Pageable pageable);
    Optional<TripDTO> getTripById(Long id);
    TripDTO updateTrip(Long id, TripDTO tripDTO);
    void deleteTrip(Long id);
}