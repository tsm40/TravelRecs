package com.example.travelrecs.dto.mapper;

import com.example.travelrecs.dto.CountryDTO;
import com.example.travelrecs.model.Country;
import com.example.travelrecs.model.State;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.stream.Collectors;

@Component
public class CountryMapper {

    // 将 Country 实体转换为 CountryDTO
    public CountryDTO toDTO(Country country) {
        if (country == null) return null;

        CountryDTO dto = new CountryDTO();
        dto.setCountryId(country.getCountryId());
        dto.setCountryName(country.getCountryName());

        // 如果需要，可以设置 stateIds
        if (country.getStates() != null) {
            List<Long> stateIds = country.getStates()
                    .stream()
                    .map(State::getStateId)
                    .collect(Collectors.toList());
            dto.setStateIds(stateIds);
        }

        return dto;
    }

    // 将 CountryDTO 转换为 Country 实体
    public Country toEntity(CountryDTO dto) {
        if (dto == null) return null;

        Country country = new Country();
        country.setCountryName(dto.getCountryName());
        // 状态列表需要在服务层处理
        return country;
    }

    // 将 CountryDTO 更新到现有的 Country 实体
    public void updateEntity(CountryDTO dto, Country country) {
        if (dto == null || country == null) return;

        country.setCountryName(dto.getCountryName());
        // 状态列表的更新需要在服务层处理
    }
}