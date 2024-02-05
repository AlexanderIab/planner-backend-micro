package com.iablonski.planner.todo.mapper;

import com.iablonski.planner.entity.Stat;
import com.iablonski.planner.todo.dto.StatDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatMapper {

    StatDTO toDTO(Stat stat);
}