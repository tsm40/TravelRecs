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
        // 检查城市名称是否已存在
        if (cityRepository.existsByCityName(cityDTO.getCityName())) {
            throw new DuplicateResourceException("City with name '" + cityDTO.getCityName() + "' already exists.");
        }

        // 获取关联的 State 实体
        State state = stateRepository.findById(cityDTO.getStateId())
                .orElseThrow(() -> new ResourceNotFoundException("State not found with id " + cityDTO.getStateId()));

        // 转换 DTO 到实体
        City city = cityMapper.toEntity(cityDTO, state);

        // 保存实体
        City savedCity = cityRepository.save(city);

        // 转换实体到 DTO
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

        // 如果城市名称有变更，检查新的名称是否已存在
        if (!city.getCityName().equals(cityDTO.getCityName())
                && cityRepository.existsByCityName(cityDTO.getCityName())) {
            throw new DuplicateResourceException("City with name '" + cityDTO.getCityName() + "' already exists.");
        }

        // 获取关联的 State 实体
        State state = stateRepository.findById(cityDTO.getStateId())
                .orElseThrow(() -> new ResourceNotFoundException("State not found with id " + cityDTO.getStateId()));

        // 更新实体
        cityMapper.updateEntity(cityDTO, city, state);

        // 保存更新
        City updatedCity = cityRepository.save(city);

        // 转换实体到 DTO
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
