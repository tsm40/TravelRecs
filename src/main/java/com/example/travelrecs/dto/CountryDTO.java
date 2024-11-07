package com.example.travelrecs.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CountryDTO {

    private Long countryId;

    private String countryName;

    // 可选：包含状态列表的 ID
    private List<Long> stateIds;
}