package com.iablonski.planner.users.service.serviceImpl;

import com.iablonski.planner.entity.User;
import com.iablonski.planner.users.dto.UserDTO;
import com.iablonski.planner.users.repository.UserRepo;
import com.iablonski.planner.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepo.findUserByEmail(email).orElseThrow();
        return UserDTO.toDTO(user);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepo.findById(id).orElseThrow();
        return UserDTO.toDTO(user);
    }

    @Override
    public void createUser(UserDTO userDTO) {
        User user = UserDTO.toUser(userDTO);
        userRepo.save(user);
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        User user = UserDTO.toUser(userDTO);
        user.setId(userDTO.id());
        userRepo.save(user);
    }

    @Override
    @Transactional
    public void deleteUserByEmail(String email) {
        userRepo.deleteUserByEmail(email);
    }

    @Override
    public void deleteUserById(Long id) {
        Optional<User> user = userRepo.findById(id);
        user.ifPresent(userRepo::delete);
    }

    @Override
    public Page<UserDTO> findByParams(String email, String username, PageRequest pageRequest) {
        Page<User> page = userRepo.findByParams(email, username, pageRequest);
        return page.map(UserDTO::toDTO);
    }
}
