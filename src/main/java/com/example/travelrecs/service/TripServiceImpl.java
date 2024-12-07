package com.example.travelrecs.service;

import com.example.travelrecs.dto.TripDTO;
import com.example.travelrecs.dto.mapper.DayMapper;
import com.example.travelrecs.dto.mapper.TripMapper;
import com.example.travelrecs.exception.ResourceNotFoundException;
import com.example.travelrecs.model.Day;
import com.example.travelrecs.model.Trip;
import com.example.travelrecs.model.User;
import com.example.travelrecs.repository.DayRepository;
import com.example.travelrecs.repository.TripRepository;
import com.example.travelrecs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DayRepository dayRepository;

    @Autowired
    private TripMapper tripMapper;

    @Autowired
    private DayMapper dayMapper;

    @Transactional
    @Override
    public TripDTO createTrip(TripDTO tripDTO) {

        User user = userRepository.findById(tripDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + tripDTO.getUserId()));


        List<Day> days = dayRepository.findAllById(tripDTO.getDayIds()).stream()
                .collect(Collectors.toList());


        Trip trip = tripMapper.toEntity(tripDTO, user, days);


        Trip savedTrip = tripRepository.save(trip);

        return tripMapper.toDTO(savedTrip);
    }

    @Override
    public Page<TripDTO> getAllTrips(Pageable pageable) {
        Page<Trip> trips = tripRepository.findAll(pageable);
        return trips.map(tripMapper::toDTO);
    }

    @Override
    public Optional<TripDTO> getTripById(Long id) {
        return tripRepository.findById(id)
                .map(tripMapper::toDTO);
    }

    @Transactional
    @Override
    public TripDTO updateTrip(Long id, TripDTO tripDTO) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found with id " + id));



        User user = userRepository.findById(tripDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + tripDTO.getUserId()));


        List<Day> days = dayRepository.findAllById(tripDTO.getDayIds()).stream()
                .collect(Collectors.toList());


        tripMapper.updateEntity(tripDTO, trip, user, days);


        Trip updatedTrip = tripRepository.save(trip);


        return tripMapper.toDTO(updatedTrip);
    }

    @Transactional
    @Override
    public void deleteTrip(Long id) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found with id " + id));

        tripRepository.delete(trip);
    }
}