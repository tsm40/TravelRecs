package com.example.travelrecs.controller;

import com.example.travelrecs.dto.CityDTO;
import com.example.travelrecs.exception.ResourceNotFoundException;
import com.example.travelrecs.model.City;
import com.example.travelrecs.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping
    public ResponseEntity<CityDTO> createCity(@RequestBody CityDTO cityDTO) {
        CityDTO createdCity = cityService.createCity(cityDTO);
        return ResponseEntity.ok(createdCity);
    }

    @GetMapping
    public ResponseEntity<List<CityDTO>> getAllCities() {
        List<CityDTO> cities = cityService.getAllCities();
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> getCityById(@PathVariable Long id) {
        CityDTO cityDTO = cityService.getCityById(id)
                .orElseThrow(() -> new ResourceNotFoundException("City not found with id " + id));
        return ResponseEntity.ok(cityDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityDTO> updateCity(@PathVariable Long id, @RequestBody CityDTO cityDTO) {
        CityDTO updatedCity = cityService.updateCity(id, cityDTO);
        return ResponseEntity.ok(updatedCity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get/{stateName}")
    public ResponseEntity<List<CityDTO>> getCityByStateName(@PathVariable String stateName) {
        List<CityDTO> cities = cityService.getCitiesByStateName(stateName);
        return ResponseEntity.ok(cities);
    }
}