package com.example.travelrecs.repository;

import com.example.travelrecs.model.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayRepository extends JpaRepository<Day, Long> {
    boolean existsByDayNumberAndTrip_TripId(int dayNumber, Long tripId);
}