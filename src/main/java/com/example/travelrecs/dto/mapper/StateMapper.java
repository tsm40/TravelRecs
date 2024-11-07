package com.example.travelrecs.dto.mapper;

import com.example.travelrecs.dto.StateDTO;
import com.example.travelrecs.model.Country;
import com.example.travelrecs.model.State;
import com.example.travelrecs.model.City;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StateMapper {

    // 将 State 实体转换为 StateDTO
    public StateDTO toDTO(State state) {
        if (state == null) return null;

        StateDTO dto = new StateDTO();
        dto.setStateId(state.getStateId());
        dto.setStateName(state.getStateName());
        dto.setCountryId(state.getCountry() != null ? state.getCountry().getCountryId() : null);

        // 如果需要，可以设置 cityIds
        if (state.getCities() != null) {
            List<Long> cityIds = state.getCities()
                    .stream()
                    .map(City::getCityId)
                    .collect(Collectors.toList());
            dto.setCityIds(cityIds);
        }

        return dto;
    }

    // 将 StateDTO 转换为 State 实体
    public State toEntity(StateDTO dto, Country country) {
        if (dto == null) return null;

        State state = new State();
        state.setStateName(dto.getStateName());
        state.setCountry(country);
        // 城市列表需要在服务层处理
        return state;
    }

    // 将 StateDTO 更新到现有的 State 实体
    public void updateEntity(StateDTO dto, State state, Country country) {
        if (dto == null || state == null) return;

        state.setStateName(dto.getStateName());
        state.setCountry(country);
        // 城市列表的更新需要在服务层处理
    }
}