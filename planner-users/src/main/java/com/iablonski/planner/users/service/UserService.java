package com.iablonski.planner.users.service;

import com.iablonski.planner.entity.User;
import com.iablonski.planner.users.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface UserService {

    UserDTO getUserByEmail(String email);
    UserDTO getUserById(Long id);
    User createUser(UserDTO userDTO);
    void updateUser(UserDTO userDTO);
    void deleteUserByEmail(String email);
    void deleteUserById(Long id);
    Page<UserDTO> findByParams(String username, String email, PageRequest pageRequest);
}
