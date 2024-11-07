package com.example.travelrecs.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityDTO {

    private Long cityId;

    private String cityName;

    private Long stateId;  // ID of the associated State
}