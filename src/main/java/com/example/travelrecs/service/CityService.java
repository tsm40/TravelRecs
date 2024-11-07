package com.example.travelrecs.service;

import com.example.travelrecs.dto.CityDTO;

import java.util.List;
import java.util.Optional;

public interface CityService {
    CityDTO createCity(CityDTO cityDTO);
    List<CityDTO> getAllCities();
    Optional<CityDTO> getCityById(Long id);
    CityDTO updateCity(Long id, CityDTO cityDTO);
    void deleteCity(Long id);
    List<CityDTO> getCitiesByStateName(String stateName);
}