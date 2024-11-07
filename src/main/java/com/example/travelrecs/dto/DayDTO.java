package com.example.travelrecs.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class DayDTO {

    private Long dayId;


    private int dayNumber;  // Day index within the trip

    private Long tripId;  // ID of the associated Trip

    private Set<Long> placeIds;  // IDs of associated Places
}