package com.example.travelrecs.repository;

import com.example.travelrecs.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    boolean existsByCityName(String cityName);
    List<City> findByStateStateName(String stateName);
}