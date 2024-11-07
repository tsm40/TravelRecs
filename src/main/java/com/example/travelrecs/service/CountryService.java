package com.example.travelrecs.service;

import com.example.travelrecs.dto.CountryDTO;
import com.example.travelrecs.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    CountryDTO createCountry(CountryDTO countryDTO);
    List<CountryDTO> getAllCountries();
    Optional<CountryDTO> getCountryById(Long id);
    CountryDTO updateCountry(Long id, CountryDTO countryDTO);
    void deleteCountry(Long id);
}