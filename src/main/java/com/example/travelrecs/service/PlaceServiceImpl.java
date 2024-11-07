package com.example.travelrecs.service;

import com.example.travelrecs.dto.PlaceDTO;
import com.example.travelrecs.dto.mapper.PlaceMapper;
import com.example.travelrecs.exception.DuplicateResourceException;
import com.example.travelrecs.exception.ResourceNotFoundException;
import com.example.travelrecs.model.City;
import com.example.travelrecs.model.Place;
import com.example.travelrecs.repository.CityRepository;
import com.example.travelrecs.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private PlaceMapper placeMapper;

    @Transactional
    @Override
    public PlaceDTO createPlace(PlaceDTO placeDTO) {
        // 检查地点名称是否已存在
        if (placeRepository.existsByName(placeDTO.getName())) {
            throw new DuplicateResourceException("Place with name '" + placeDTO.getName() + "' already exists.");
        }

        // 获取关联的 City 实体
        City city = cityRepository.findById(placeDTO.getCityId())
                .orElseThrow(() -> new ResourceNotFoundException("City not found with id " + placeDTO.getCityId()));

        // 转换 DTO 到实体
        Place place = placeMapper.toEntity(placeDTO, city);

        // 保存实体
        Place savedPlace = placeRepository.save(place);

        // 转换实体到 DTO
        return placeMapper.toDTO(savedPlace);
    }

    @Override
    public Page<PlaceDTO> getAllPlaces(Pageable pageable) {
        Page<Place> places = placeRepository.findAll(pageable);
        return places.map(placeMapper::toDTO);
    }

    @Override
    public Optional<PlaceDTO> getPlaceById(Long id) {
        return placeRepository.findById(id)
                .map(placeMapper::toDTO);
    }

    @Transactional
    @Override
    public PlaceDTO updatePlace(Long id, PlaceDTO placeDTO) {
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Place not found with id " + id));

        // 如果地点名称有变更，检查新的名称是否已存在
        if (!place.getName().equals(placeDTO.getName())
                && placeRepository.existsByName(placeDTO.getName())) {
            throw new DuplicateResourceException("Place with name '" + placeDTO.getName() + "' already exists.");
        }

        // 获取关联的 City 实体
        City city = cityRepository.findById(placeDTO.getCityId())
                .orElseThrow(() -> new ResourceNotFoundException("City not found with id " + placeDTO.getCityId()));

        // 更新实体
        placeMapper.updateEntity(placeDTO, place, city);

        // 保存更新
        Place updatedPlace = placeRepository.save(place);

        // 转换实体到 DTO
        return placeMapper.toDTO(updatedPlace);
    }

    @Transactional
    @Override
    public void deletePlace(Long id) {
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Place not found with id " + id));

        placeRepository.delete(place);
    }

    @Override
    public Page<PlaceDTO> getPlacesByCityId(Long cityId, Pageable pageable) {
        Page<Place> places = placeRepository.findByCityCityId(cityId, pageable);
        if (places.isEmpty()) {
            throw new ResourceNotFoundException("No places found for city with ID '" + cityId + "'.");
        }
        return places.map(placeMapper::toDTO);
    }

    @Override
    public Page<PlaceDTO> getPlacesByCityName(String cityName, Pageable pageable) {
        Page<Place> places = placeRepository.findByCityCityName(cityName, pageable);
        if (places.isEmpty()) {
            throw new ResourceNotFoundException("No places found for city with name '" + cityName + "'.");
        }
        return places.map(placeMapper::toDTO);
    }
}