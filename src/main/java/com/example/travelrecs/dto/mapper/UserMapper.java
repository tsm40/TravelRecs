package com.example.travelrecs.dto.mapper;

import com.example.travelrecs.dto.UserDTO;
import com.example.travelrecs.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(User user) {
        if (user == null) return null;

        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setUserName(user.getUserName());
        dto.setPassword(null);
        return dto;
    }

    public User toEntity(UserDTO dto) {
        if (dto == null) return null;

        User user = new User();
        user.setUserName(dto.getUserName());
        user.setPassword(dto.getPassword());
        return user;
    }

    public void updateEntity(UserDTO dto, User user) {
        if (dto == null || user == null) return;

        user.setUserName(dto.getUserName());
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            user.setPassword(dto.getPassword());
        }
    }
}