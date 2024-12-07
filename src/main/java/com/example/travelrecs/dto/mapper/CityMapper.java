package com.example.travelrecs.dto.mapper;

import com.example.travelrecs.dto.CityDTO;
import com.example.travelrecs.model.City;
import com.example.travelrecs.model.State;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {

    public CityDTO toDTO(City city) {
        if (city == null) return null;

        CityDTO dto = new CityDTO();
        dto.setCityId(city.getCityId());
        dto.setCityName(city.getCityName());
        dto.setStateId(city.getState() != null ? city.getState().getStateId() : null);
        return dto;
    }

    public City toEntity(CityDTO dto, State state) {
        if (dto == null) return null;

        City city = new City();
        city.setCityName(dto.getCityName());
        city.setState(state);
        return city;
    }

    public void updateEntity(CityDTO dto, City city, State state) {
        if (dto == null || city == null) return;

        city.setCityName(dto.getCityName());
        city.setState(state);
    }
}