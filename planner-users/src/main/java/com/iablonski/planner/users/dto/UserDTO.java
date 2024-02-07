package com.iablonski.planner.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iablonski.planner.entity.Role;

import java.util.Set;

public record UserDTO(Long id,
                      String email,
                      @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
                      String password,
                      String username) {
}