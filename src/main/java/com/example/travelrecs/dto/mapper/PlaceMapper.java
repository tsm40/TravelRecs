package com.example.travelrecs.dto.mapper;

import com.example.travelrecs.dto.PlaceDTO;
import com.example.travelrecs.model.City;
import com.example.travelrecs.model.Place;
import org.springframework.stereotype.Component;

@Component
public class PlaceMapper {

    // 将 Place 实体转换为 PlaceDTO
    public PlaceDTO toDTO(Place place) {
        if (place == null) return null;

        PlaceDTO dto = new PlaceDTO();
        dto.setPlaceId(place.getPlaceId());
        dto.setLatitude(place.getLatitude());
        dto.setLongitude(place.getLongitude());
        dto.setName(place.getName());
        dto.setDescription(place.getDescription());
        dto.setCategory(place.getCategory());
        dto.setRating(place.getRating());
        dto.setCityId(place.getCity() != null ? place.getCity().getCityId() : null);
        dto.setUpperCost(place.getUpperCost());
        dto.setLowerCost(place.getLowerCost());
        dto.setDuration(place.getDuration());
        dto.setPhotoURL(place.getPhotoURL());
        return dto;
    }

    // 将 PlaceDTO 转换为 Place 实体
    public Place toEntity(PlaceDTO dto, City city) {
        if (dto == null) return null;

        Place place = new Place();
        place.setLatitude(dto.getLatitude());
        place.setLongitude(dto.getLongitude());
        place.setName(dto.getName());
        place.setDescription(dto.getDescription());
        place.setCategory(dto.getCategory());
        place.setRating(dto.getRating());
        place.setCity(city);
        place.setUpperCost(dto.getUpperCost());
        place.setLowerCost(dto.getLowerCost());
        place.setDuration(dto.getDuration());
        place.setPhotoURL(dto.getPhotoURL());
        return place;
    }

    // 将 PlaceDTO 更新到现有的 Place 实体
    public void updateEntity(PlaceDTO dto, Place place, City city) {
        if (dto == null || place == null) return;

        place.setLatitude(dto.getLatitude());
        place.setLongitude(dto.getLongitude());
        place.setName(dto.getName());
        place.setDescription(dto.getDescription());
        place.setCategory(dto.getCategory());
        place.setRating(dto.getRating());
        place.setCity(city);
        place.setUpperCost(dto.getUpperCost());
        place.setLowerCost(dto.getLowerCost());
        place.setDuration(dto.getDuration());
        place.setPhotoURL(dto.getPhotoURL());
    }
}