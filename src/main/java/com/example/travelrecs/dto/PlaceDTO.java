package com.example.travelrecs.dto;

import com.example.travelrecs.model.Place;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceDTO {

    private Long placeId;

//    @NotNull(message = "Latitude cannot be null")
    private double latitude;  // Exact location - Latitude

//    @NotNull(message = "Longitude cannot be null")
    private double longitude; // Exact location - Longitude

//    @NotBlank(message = "Name cannot be blank")
//    @Size(max = 255, message = "Name must be less than 255 characters")
    private String name;  // Name of the place

//    @NotBlank(message = "Description cannot be blank")
//    @Size(max = 1000, message = "Description must be less than 1000 characters")
    private String description;  // Detailed description of the place

//    @NotNull(message = "Category cannot be null")
    private Place.Category category;  // Category (Attractions, Dining, Accommodations)

//    @DecimalMin(value = "0.0", inclusive = true, message = "Rating must be at least 0.0")
//    @DecimalMax(value = "5.0", inclusive = true, message = "Rating must be at most 5.0")
    private float rating;  // Rating with two decimal precision

//    @NotNull(message = "City ID cannot be null")
    private Long cityId;  // ID of the associated City

//    @Min(value = 0, message = "Upper cost must be non-negative")
    private int upperCost;  // Upper cost estimate for this place

//    @Min(value = 0, message = "Lower cost must be non-negative")
    private int lowerCost;  // Lower cost estimate for this place

//    @DecimalMin(value = "0.0", inclusive = true, message = "Duration must be at least 0.0")
    private float duration;  // Estimated duration to spend at this place (single decimal precision)

//    @NotBlank(message = "Photo URL cannot be blank")
//    @Size(max = 500, message = "Photo URL must be less than 500 characters")
    private String photoURL;  // URL to a photo of the place
}