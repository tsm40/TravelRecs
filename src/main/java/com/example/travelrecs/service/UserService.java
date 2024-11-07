package com.example.travelrecs.service;

import com.example.travelrecs.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    Page<UserDTO> getAllUsers(Pageable pageable);
    Optional<UserDTO> getUserById(Long id);
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
}