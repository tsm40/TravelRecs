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
        // 检查用户名是否已存在
        if (userRepository.existsByUserName(userDTO.getUserName())) {
            throw new DuplicateResourceException("用户名 '" + userDTO.getUserName() + "' 已存在。");
        }

        // 转换 DTO 到实体
        User user = userMapper.toEntity(userDTO);
        // 加密密码
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPassword(user.getPassword());

        // 保存实体
        User savedUser = userRepository.save(user);

        // 转换实体到 DTO
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
                .orElseThrow(() -> new ResourceNotFoundException("用户未找到，ID：" + id));

        // 如果用户名有变更，检查新用户名是否已存在
        if (!existingUser.getUserName().equals(userDTO.getUserName()) &&
                userRepository.existsByUserName(userDTO.getUserName())) {
            throw new DuplicateResourceException("用户名 '" + userDTO.getUserName() + "' 已存在。");
        }

        // 更新实体
        userMapper.updateEntity(userDTO, existingUser);

        // 如果密码有变更，进行加密
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
//            existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            existingUser.setPassword(userDTO.getPassword());
        }

        // 保存更新
        User updatedUser = userRepository.save(existingUser);

        // 转换实体到 DTO
        return userMapper.toDTO(updatedUser);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("用户未找到，ID：" + id));

        userRepository.delete(existingUser);
    }
}
