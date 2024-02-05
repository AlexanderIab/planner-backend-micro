package com.iablonski.planner.users.mapper;

import com.iablonski.planner.entity.User;
import com.iablonski.planner.users.dto.UserDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserDTO userDTO);
    @InheritInverseConfiguration
    UserDTO toDTO(User user);
}
