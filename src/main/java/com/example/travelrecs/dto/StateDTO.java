package com.example.travelrecs.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StateDTO {

    private Long stateId;


    private String stateName;


    private Long countryId;  // ID of the associated Country

    // Optional: List of city IDs associated with the state
    private List<Long> cityIds;
}