package com.example.travelrecs.service;

import com.example.travelrecs.dto.DayDTO;
import com.example.travelrecs.dto.mapper.DayMapper;
import com.example.travelrecs.exception.ResourceNotFoundException;
import com.example.travelrecs.model.Day;
import com.example.travelrecs.model.Place;
import com.example.travelrecs.model.Trip;
import com.example.travelrecs.repository.DayRepository;
import com.example.travelrecs.repository.PlaceRepository;
import com.example.travelrecs.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DayServiceImpl implements DayService {

    @Autowired
    private DayRepository dayRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private DayMapper dayMapper;

    @Transactional
    @Override
    public DayDTO createDay(DayDTO dayDTO) {
        // Validate and retrieve associated Trip
        Trip trip = null;
        if (dayDTO.getTripId() != null) {
            trip = tripRepository.findById(dayDTO.getTripId())
                    .orElseThrow(() -> new ResourceNotFoundException("Trip not found with id " + dayDTO.getTripId()));
        }

        // Retrieve associated Places
        Set<Place> places = placeRepository.findAllById(dayDTO.getPlaceIds())
                .stream().collect(Collectors.toSet());

        // Convert DTO to entity
        Day day = dayMapper.toEntity(dayDTO, trip, places);

        // Save entity
        Day savedDay = dayRepository.save(day);

        // Convert back to DTO
        return dayMapper.toDTO(savedDay);
    }

    @Override
    public Page<DayDTO> getAllDays(Pageable pageable) {
        return dayRepository.findAll(pageable)
                .map(dayMapper::toDTO);
    }

    @Override
    public Optional<DayDTO> getDayById(Long id) {
        return dayRepository.findById(id)
                .map(dayMapper::toDTO);
    }

    @Transactional
    @Override
    public DayDTO updateDay(Long id, DayDTO dayDTO) {
        Day existingDay = dayRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Day not found with id " + id));

        // Validate and retrieve associated Trip
        Trip trip = null;
        if (dayDTO.getTripId() != null) {
            trip = tripRepository.findById(dayDTO.getTripId())
                    .orElseThrow(() -> new ResourceNotFoundException("Trip not found with id " + dayDTO.getTripId()));
        }

        // Retrieve associated Places
        Set<Place> places = placeRepository.findAllById(dayDTO.getPlaceIds())
                .stream().collect(Collectors.toSet());

        // Update entity
        dayMapper.updateEntity(dayDTO, existingDay, trip, places);

        // Save updated entity
        Day updatedDay = dayRepository.save(existingDay);

        // Convert back to DTO
        return dayMapper.toDTO(updatedDay);
    }

    @Transactional
    @Override
    public void deleteDay(Long id) {
        Day existingDay = dayRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Day not found with id " + id));

        dayRepository.delete(existingDay);
    }
}
