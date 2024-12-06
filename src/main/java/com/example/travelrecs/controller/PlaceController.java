package com.example.travelrecs.controller;


import com.example.travelrecs.dto.PlaceDTO;
import com.example.travelrecs.exception.ResourceNotFoundException;
import com.example.travelrecs.model.Place;
import com.example.travelrecs.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/places")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @PostMapping
    public ResponseEntity<PlaceDTO> createPlace(@RequestBody PlaceDTO placeDTO) {
        PlaceDTO createdPlace = placeService.createPlace(placeDTO);
        return ResponseEntity.ok(createdPlace);
    }

    @GetMapping
    public ResponseEntity<Page<PlaceDTO>> getAllPlaces(Pageable pageable) {
        Page<PlaceDTO> places = placeService.getAllPlaces(pageable);
        return ResponseEntity.ok(places);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaceDTO> getPlaceById(@PathVariable Long id) {
        PlaceDTO placeDTO = placeService.getPlaceById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Place not found with id " + id));
        return ResponseEntity.ok(placeDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlaceDTO> updatePlace(@PathVariable Long id, @RequestBody PlaceDTO placeDTO) {
        PlaceDTO updatedPlace = placeService.updatePlace(id, placeDTO);
        return ResponseEntity.ok(updatedPlace);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlace(@PathVariable Long id) {
        placeService.deletePlace(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getByCity/{cityId}")
    public ResponseEntity<Page<PlaceDTO>> getPlaceByCityId(@PathVariable Long cityId, Pageable pageable) {
        Page<PlaceDTO> places = placeService.getPlacesByCityId(cityId, pageable);
        return ResponseEntity.ok(places);
    }

    @GetMapping("/getByCityName/{cityName}")
    public ResponseEntity<Page<PlaceDTO>> getPlacesByCityName(@PathVariable String cityName, Pageable pageable) {
        Page<PlaceDTO> places = placeService.getPlacesByCityName(cityName, pageable);
        return ResponseEntity.ok(places);
    }
}