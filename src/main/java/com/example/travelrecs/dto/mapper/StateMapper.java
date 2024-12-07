package com.example.travelrecs.dto.mapper;

import com.example.travelrecs.dto.StateDTO;
import com.example.travelrecs.model.Country;
import com.example.travelrecs.model.State;
import com.example.travelrecs.model.City;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StateMapper {

    public StateDTO toDTO(State state) {
        if (state == null) return null;

        StateDTO dto = new StateDTO();
        dto.setStateId(state.getStateId());
        dto.setStateName(state.getStateName());
        dto.setCountryId(state.getCountry() != null ? state.getCountry().getCountryId() : null);

        if (state.getCities() != null) {
            List<Long> cityIds = state.getCities()
                    .stream()
                    .map(City::getCityId)
                    .collect(Collectors.toList());
            dto.setCityIds(cityIds);
        }

        return dto;
    }

    public State toEntity(StateDTO dto, Country country) {
        if (dto == null) return null;

        State state = new State();
        state.setStateName(dto.getStateName());
        state.setCountry(country);
        return state;
    }

    public void updateEntity(StateDTO dto, State state, Country country) {
        if (dto == null || state == null) return;

        state.setStateName(dto.getStateName());
        state.setCountry(country);
    }
}