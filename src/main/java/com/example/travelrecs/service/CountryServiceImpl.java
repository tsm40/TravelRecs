package com.example.travelrecs.service;

import com.example.travelrecs.dto.CountryDTO;
import com.example.travelrecs.dto.mapper.CountryMapper;
import com.example.travelrecs.exception.DuplicateResourceException;
import com.example.travelrecs.exception.ResourceNotFoundException;
import com.example.travelrecs.model.Country;
import com.example.travelrecs.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CountryMapper countryMapper;

    @Transactional
    @Override
    public CountryDTO createCountry(CountryDTO countryDTO) {
        // 检查国家名称是否已存在
        if (countryRepository.existsByCountryName(countryDTO.getCountryName())) {
            throw new DuplicateResourceException("Country with name '" + countryDTO.getCountryName() + "' already exists.");
        }

        // 转换 DTO 到实体
        Country country = countryMapper.toEntity(countryDTO);

        // 保存实体
        Country savedCountry = countryRepository.save(country);

        // 转换实体到 DTO
        return countryMapper.toDTO(savedCountry);
    }

    @Override
    public List<CountryDTO> getAllCountries() {
        List<Country> countries = countryRepository.findAll();
        return countries.stream()
                .map(countryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CountryDTO> getCountryById(Long id) {
        return countryRepository.findById(id)
                .map(countryMapper::toDTO);
    }

    @Transactional
    @Override
    public CountryDTO updateCountry(Long id, CountryDTO countryDTO) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country not found with id " + id));

        // 如果国家名称有变更，检查新的名称是否已存在
        if (!country.getCountryName().equals(countryDTO.getCountryName())
                && countryRepository.existsByCountryName(countryDTO.getCountryName())) {
            throw new DuplicateResourceException("Country with name '" + countryDTO.getCountryName() + "' already exists.");
        }

        // 更新实体
        countryMapper.updateEntity(countryDTO, country);

        // 保存更新
        Country updatedCountry = countryRepository.save(country);

        // 转换实体到 DTO
        return countryMapper.toDTO(updatedCountry);
    }

    @Transactional
    @Override
    public void deleteCountry(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country not found with id " + id));

        countryRepository.delete(country);
    }
}
