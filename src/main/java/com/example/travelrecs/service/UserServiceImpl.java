package com.example.travelrecs.service;

import com.example.travelrecs.dto.UserDTO;
import com.example.travelrecs.dto.mapper.UserMapper;
import com.example.travelrecs.exception.DuplicateResourceException;
import com.example.travelrecs.exception.ResourceNotFoundException;
import com.example.travelrecs.model.User;
import com.example.travelrecs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

//    @Autowired
//    private PasswordEncoder passwordEncoder;  // 用于加密密码

    @Transactional
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if (userRepository.existsByUserName(userDTO.getUserName())) {
            throw new DuplicateResourceException("Username '" + userDTO.getUserName() + "' already exists.");
        }

        User user = userMapper.toEntity(userDTO);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPassword(user.getPassword());

        User savedUser = userRepository.save(user);

        return userMapper.toDTO(savedUser);
    }

    @Override
    public Page<UserDTO> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::toDTO);
    }

    @Override
    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO);
    }

    @Transactional
    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User note found，ID：" + id));

        if (!existingUser.getUserName().equals(userDTO.getUserName()) &&
                userRepository.existsByUserName(userDTO.getUserName())) {
            throw new DuplicateResourceException("User name '" + userDTO.getUserName() + "' already exists.");
        }

        userMapper.updateEntity(userDTO, existingUser);

        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
//            existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            existingUser.setPassword(userDTO.getPassword());
        }

        User updatedUser = userRepository.save(existingUser);

        return userMapper.toDTO(updatedUser);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found，ID：" + id));

        userRepository.delete(existingUser);
    }
}
