package com.example.travelrecs.dto.mapper;

import com.example.travelrecs.dto.UserDTO;
import com.example.travelrecs.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    // 将 User 实体转换为 UserDTO
    public UserDTO toDTO(User user) {
        if (user == null) return null;

        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setUserName(user.getUserName());
        // 通常不在响应中返回密码，以下行可根据需求决定是否包含
        dto.setPassword(null);  // 或者完全省略设置密码
        return dto;
    }

    // 将 UserDTO 转换为 User 实体
    public User toEntity(UserDTO dto) {
        if (dto == null) return null;

        User user = new User();
        user.setUserName(dto.getUserName());
        user.setPassword(dto.getPassword());  // 确保密码已加密
        return user;
    }

    // 更新现有的 User 实体
    public void updateEntity(UserDTO dto, User user) {
        if (dto == null || user == null) return;

        user.setUserName(dto.getUserName());
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            user.setPassword(dto.getPassword());  // 确保密码已加密
        }
    }
}