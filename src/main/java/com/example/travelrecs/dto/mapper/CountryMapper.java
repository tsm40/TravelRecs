package com.example.travelrecs.dto.mapper;

import com.example.travelrecs.dto.CountryDTO;
import com.example.travelrecs.model.Country;
import com.example.travelrecs.model.State;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.stream.Collectors;

@Component
public class CountryMapper {

    public CountryDTO toDTO(Country country) {
        if (country == null) return null;

        CountryDTO dto = new CountryDTO();
        dto.setCountryId(country.getCountryId());
        dto.setCountryName(country.getCountryName());

        if (country.getStates() != null) {
            List<Long> stateIds = country.getStates()
                    .stream()
                    .map(State::getStateId)
                    .collect(Collectors.toList());
            dto.setStateIds(stateIds);
        }

        return dto;
    }

    public Country toEntity(CountryDTO dto) {
        if (dto == null) return null;

        Country country = new Country();
        country.setCountryName(dto.getCountryName());
        return country;
    }

    public void updateEntity(CountryDTO dto, Country country) {
        if (dto == null || country == null) return;

        country.setCountryName(dto.getCountryName());
    }
}