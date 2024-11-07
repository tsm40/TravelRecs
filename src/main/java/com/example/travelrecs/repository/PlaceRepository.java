package com.example.travelrecs.repository;

import com.example.travelrecs.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    boolean existsByName(String name);

    // find places by city id, support pagination


    Page<Place> findByCityCityId(Long cityId, Pageable pageable);

    // find places by city name, support pagination
    Page<Place> findByCityCityName(String cityName, Pageable pageable);

}