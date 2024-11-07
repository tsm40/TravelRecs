package com.example.travelrecs.service;

import com.example.travelrecs.dto.StateDTO;

import java.util.List;
import java.util.Optional;

public interface StateService {
    StateDTO createState(StateDTO stateDTO);
    List<StateDTO> getAllStates();
    Optional<StateDTO> getStateById(Long id);
    StateDTO updateState(Long id, StateDTO stateDTO);
    List<StateDTO> getStatesByCountryName(String countryName);
    void deleteState(Long id);
}