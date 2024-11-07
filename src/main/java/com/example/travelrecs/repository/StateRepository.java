package com.example.travelrecs.repository;

import com.example.travelrecs.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    boolean existsByStateName(String stateName);
    List<State> findByCountryCountryName(String countryName);
}