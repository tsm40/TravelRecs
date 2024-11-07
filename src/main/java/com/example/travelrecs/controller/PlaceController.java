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

    // 创建 Place
    @PostMapping
    public ResponseEntity<PlaceDTO> createPlace(@RequestBody PlaceDTO placeDTO) {
        PlaceDTO createdPlace = placeService.createPlace(placeDTO);
        return ResponseEntity.ok(createdPlace);
    }

    // 获取所有 Places，支持分页
    @GetMapping
    public ResponseEntity<Page<PlaceDTO>> getAllPlaces(Pageable pageable) {
        Page<PlaceDTO> places = placeService.getAllPlaces(pageable);
        return ResponseEntity.ok(places);
    }

    // 根据 ID 获取 Place
    @GetMapping("/{id}")
    public ResponseEntity<PlaceDTO> getPlaceById(@PathVariable Long id) {
        PlaceDTO placeDTO = placeService.getPlaceById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Place not found with id " + id));
        return ResponseEntity.ok(placeDTO);
    }

    // 更新 Place
    @PutMapping("/{id}")
    public ResponseEntity<PlaceDTO> updatePlace(@PathVariable Long id, @RequestBody PlaceDTO placeDTO) {
        PlaceDTO updatedPlace = placeService.updatePlace(id, placeDTO);
        return ResponseEntity.ok(updatedPlace);
    }

    // 删除 Place
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlace(@PathVariable Long id) {
        placeService.deletePlace(id);
        return ResponseEntity.noContent().build();
    }

    // 根据城市ID获取地点列表，支持分页
    @GetMapping("/getByCity/{cityId}")
    public ResponseEntity<Page<PlaceDTO>> getPlaceByCityId(@PathVariable Long cityId, Pageable pageable) {
        Page<PlaceDTO> places = placeService.getPlacesByCityId(cityId, pageable);
        return ResponseEntity.ok(places);
    }

    // 根据城市名称获取地点列表，支持分页
    @GetMapping("/getByCityName/{cityName}")
    public ResponseEntity<Page<PlaceDTO>> getPlacesByCityName(@PathVariable String cityName, Pageable pageable) {
        Page<PlaceDTO> places = placeService.getPlacesByCityName(cityName, pageable);
        return ResponseEntity.ok(places);
    }
}