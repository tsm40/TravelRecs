package com.example.travelrecs.service;

import com.example.travelrecs.dto.StateDTO;
import com.example.travelrecs.dto.mapper.StateMapper;
import com.example.travelrecs.exception.DuplicateResourceException;
import com.example.travelrecs.exception.ResourceNotFoundException;
import com.example.travelrecs.model.Country;
import com.example.travelrecs.model.State;
import com.example.travelrecs.repository.CityRepository;
import com.example.travelrecs.repository.CountryRepository;
import com.example.travelrecs.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StateServiceImpl implements StateService {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateMapper stateMapper;

    @Transactional
    @Override
    public StateDTO createState(StateDTO stateDTO) {
        if (stateRepository.existsByStateName(stateDTO.getStateName())) {
            throw new DuplicateResourceException("State with name '" + stateDTO.getStateName() + "' already exists.");
        }

        Country country = countryRepository.findById(stateDTO.getCountryId())
                .orElseThrow(() -> new ResourceNotFoundException("Country not found with id " + stateDTO.getCountryId()));

        State state = stateMapper.toEntity(stateDTO, country);

        State savedState = stateRepository.save(state);

        return stateMapper.toDTO(savedState);
    }

    @Override
    public List<StateDTO> getAllStates() {
        List<State> states = stateRepository.findAll();
        return states.stream()
                .map(stateMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<StateDTO> getStateById(Long id) {
        return stateRepository.findById(id)
                .map(stateMapper::toDTO);
    }

    @Transactional
    @Override
    public StateDTO updateState(Long id, StateDTO stateDTO) {
        State state = stateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("State not found with id " + id));

        if (!state.getStateName().equals(stateDTO.getStateName())
                && stateRepository.existsByStateName(stateDTO.getStateName())) {
            throw new DuplicateResourceException("State with name '" + stateDTO.getStateName() + "' already exists.");
        }

        Country country = countryRepository.findById(stateDTO.getCountryId())
                .orElseThrow(() -> new ResourceNotFoundException("Country not found with id " + stateDTO.getCountryId()));

        stateMapper.updateEntity(stateDTO, state, country);

        State updatedState = stateRepository.save(state);

        return stateMapper.toDTO(updatedState);
    }

    @Transactional
    @Override
    public void deleteState(Long id) {
        State state = stateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("State not found with id " + id));

        stateRepository.delete(state);
    }

    @Override
    public List<StateDTO> getStatesByCountryName(String countryName) {
        List<State> states = stateRepository.findByCountryCountryName(countryName);
        if (states.isEmpty()) {
            throw new ResourceNotFoundException("No states found for country with name '" + countryName + "'.");
        }
        return states.stream()
                .map(stateMapper::toDTO)
                .collect(Collectors.toList());
    }
}