package com.example.travelrecs.service;

import com.example.travelrecs.dto.CityDTO;
import com.example.travelrecs.dto.mapper.CityMapper;
import com.example.travelrecs.exception.DuplicateResourceException;
import com.example.travelrecs.exception.ResourceNotFoundException;
import com.example.travelrecs.model.City;
import com.example.travelrecs.model.State;
import com.example.travelrecs.repository.CityRepository;
import com.example.travelrecs.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;  // 假设您有 StateRepository

    @Autowired
    private CityMapper cityMapper;

    @Transactional
    @Override
    public CityDTO createCity(CityDTO cityDTO) {
        if (cityRepository.existsByCityName(cityDTO.getCityName())) {
            throw new DuplicateResourceException("City with name '" + cityDTO.getCityName() + "' already exists.");
        }

        State state = stateRepository.findById(cityDTO.getStateId())
                .orElseThrow(() -> new ResourceNotFoundException("State not found with id " + cityDTO.getStateId()));

        City city = cityMapper.toEntity(cityDTO, state);

        City savedCity = cityRepository.save(city);

        return cityMapper.toDTO(savedCity);
    }

    @Override
    public List<CityDTO> getAllCities() {
        List<City> cities = cityRepository.findAll();
        return cities.stream()
                .map(cityMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CityDTO> getCityById(Long id) {
        return cityRepository.findById(id)
                .map(cityMapper::toDTO);
    }

    @Transactional
    @Override
    public CityDTO updateCity(Long id, CityDTO cityDTO) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("City not found with id " + id));

        if (!city.getCityName().equals(cityDTO.getCityName())
                && cityRepository.existsByCityName(cityDTO.getCityName())) {
            throw new DuplicateResourceException("City with name '" + cityDTO.getCityName() + "' already exists.");
        }

        State state = stateRepository.findById(cityDTO.getStateId())
                .orElseThrow(() -> new ResourceNotFoundException("State not found with id " + cityDTO.getStateId()));

        cityMapper.updateEntity(cityDTO, city, state);

        City updatedCity = cityRepository.save(city);

        return cityMapper.toDTO(updatedCity);
    }

    @Transactional
    @Override
    public void deleteCity(Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("City not found with id " + id));

        cityRepository.delete(city);
    }

    @Override
    public List<CityDTO> getCitiesByStateName(String stateName) {
        List<City> cities = cityRepository.findByStateStateName(stateName);
        if (cities.isEmpty()) {
            throw new ResourceNotFoundException("No cities found for state with name '" + stateName + "'.");
        }
        return cities.stream()
                .map(cityMapper::toDTO)
                .collect(Collectors.toList());
    }
}
