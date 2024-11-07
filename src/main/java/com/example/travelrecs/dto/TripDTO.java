package com.example.travelrecs.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TripDTO {

    private Long tripId;

//    @Min(value = 0, message = "Total budget must be non-negative")
    private int totalBudget;  // Planned budget for the trip

//    @Min(value = 0, message = "Current budget must be non-negative")
    private int currBudget;  // Current budget for the trip with places considered

//    @NotBlank(message = "Start date cannot be blank")
//    @Size(max = 50, message = "Start date must be less than 50 characters")
    private String startDate;  // Start date of the trip

//    @NotBlank(message = "End date cannot be blank")
//    @Size(max = 50, message = "End date must be less than 50 characters")
    private String endDate;  // End date of the trip

//    @NotNull(message = "User ID cannot be null")
    private Long userId;  // ID of the associated User

    private List<Long> dayIds;  // IDs of associated Days
}
