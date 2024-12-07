package com.example.travelrecs.dto.mapper;

import com.example.travelrecs.dto.TripDTO;
import com.example.travelrecs.model.Day;
import com.example.travelrecs.model.Trip;
import com.example.travelrecs.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TripMapper {

    public TripDTO toDTO(Trip trip) {
        if (trip == null) return null;

        TripDTO dto = new TripDTO();
        dto.setTripId(trip.getTripId());
        dto.setTotalBudget(trip.getTotalBudget());
        dto.setCurrBudget(trip.getCurrBudget());
        dto.setStartDate(trip.getStartDate());
        dto.setEndDate(trip.getEndDate());
        dto.setUserId(trip.getUser() != null ? trip.getUser().getUserId() : null);
        dto.setDayIds(trip.getDays().stream()
                .map(Day::getDayId)
                .collect(Collectors.toList()));
        return dto;
    }

    public Trip toEntity(TripDTO dto, User user, List<Day> days) {
        if (dto == null) return null;

        Trip trip = new Trip();
        trip.setTotalBudget(dto.getTotalBudget());
        trip.setCurrBudget(dto.getCurrBudget());
        trip.setStartDate(dto.getStartDate());
        trip.setEndDate(dto.getEndDate());
        trip.setUser(user);
        trip.setDays(days);
        return trip;
    }

    public void updateEntity(TripDTO dto, Trip trip, User user, List<Day> days) {
        if (dto == null || trip == null) return;

        trip.setTotalBudget(dto.getTotalBudget());
        trip.setCurrBudget(dto.getCurrBudget());
        trip.setStartDate(dto.getStartDate());
        trip.setEndDate(dto.getEndDate());
        trip.setUser(user);
        trip.setDays(days);
    }
}