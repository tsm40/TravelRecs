package com.example.travelrecs.dto.mapper;

import com.example.travelrecs.dto.DayDTO;
import com.example.travelrecs.model.Day;
import com.example.travelrecs.model.Place;
import com.example.travelrecs.model.Trip;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DayMapper {

    // 将 Day 实体转换为 DayDTO
    public DayDTO toDTO(Day day) {
        if (day == null) return null;

        DayDTO dto = new DayDTO();
        dto.setDayId(day.getDayId());
        dto.setDayNumber(day.getDayNumber());
        dto.setTripId(day.getTrip() != null ? day.getTrip().getTripId() : null);
        dto.setPlaceIds(day.getPlaces().stream()
                .map(Place::getPlaceId)
                .collect(Collectors.toSet()));
        return dto;
    }

    // 将 DayDTO 转换为 Day 实体
    public Day toEntity(DayDTO dto, Trip trip, Set<Place> places) {
        if (dto == null) return null;

        Day day = new Day();
        day.setDayNumber(dto.getDayNumber());
        day.setTrip(trip);
        day.setPlaces(places);
        return day;
    }

    // 将 DayDTO 更新到现有的 Day 实体
    public void updateEntity(DayDTO dto, Day day, Trip trip, Set<Place> places) {
        if (dto == null || day == null) return;

        day.setDayNumber(dto.getDayNumber());
        day.setTrip(trip);
        day.setPlaces(places);
    }
}
