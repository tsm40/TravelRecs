package com.example.travelrecs.service;

import com.example.travelrecs.dto.PlaceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PlaceService {
    PlaceDTO createPlace(PlaceDTO placeDTO);
    Page<PlaceDTO> getAllPlaces(Pageable pageable);
    Optional<PlaceDTO> getPlaceById(Long id);
    PlaceDTO updatePlace(Long id, PlaceDTO placeDTO);
    void deletePlace(Long id);

    Page<PlaceDTO> getPlacesByCityId(Long cityId, Pageable pageable);

    Page<PlaceDTO> getPlacesByCityName(String cityName, Pageable pageable);
}