package com.iablonski.planner.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iablonski.planner.entity.Role;
import com.iablonski.planner.entity.User;

import java.util.Set;

public record UserDTO(Long id,
                      String email,
                      @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
                      String password,
                      String username,
                      @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
                      Set<Role> roles) {

    public static UserDTO toDTO(User user){
        return new UserDTO(user.getId(), user.getEmail(), user.getPassword(), user.getUsername(), user.getRoles());
    }

    public static User toUser(UserDTO userDTO){
        User user = new User();
        user.setEmail(userDTO.email);
        user.setUsername(userDTO.username);
        user.setPassword(userDTO.password);
        user.setRoles(userDTO.roles);
        return user;
    }
}
