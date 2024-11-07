package com.example.travelrecs.repository;

import com.example.travelrecs.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    boolean existsByCountryName(String countryName);
    Optional<Country> findByCountryName(String countryName);
}