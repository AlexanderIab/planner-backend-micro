package com.iablonski.planner.users.service.serviceImpl;

import com.iablonski.planner.entity.User;
import com.iablonski.planner.users.dto.UserDTO;
import com.iablonski.planner.users.mapper.UserMapper;
import com.iablonski.planner.users.repository.UserRepo;
import com.iablonski.planner.users.response.MessageResponse;
import com.iablonski.planner.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepo.findUserByEmail(email).orElseThrow();
        return userMapper.toDTO(user);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepo.findById(id).orElseThrow();
        return userMapper.toDTO(user);
    }

    @Override
    public void createUser(UserDTO userDTO) {
        User user = userMapper.toUser(userDTO);
        user.setId(null);
        userRepo.save(user);
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        if (!userRepo.existsById(userDTO.id())) throw new RuntimeException("User already exists");
        User user = userMapper.toUser(userDTO);
        userRepo.save(user);
    }

    @Override
    @Transactional
    public void deleteUserByEmail(String email) {
        User user = userRepo.findUserByEmail(email).orElseThrow();
        userRepo.delete(user);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = userRepo.findById(id).orElseThrow();
        userRepo.delete(user);
    }

    @Override
    public Page<UserDTO> findByParams(String email, String username, PageRequest pageRequest) {
        Page<User> page = userRepo.findByParams(email, username, pageRequest);
        return page.map(userMapper::toDTO);
    }
}