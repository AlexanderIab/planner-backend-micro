package com.iablonski.planner.todo.mapper;

import com.iablonski.planner.entity.Priority;
import com.iablonski.planner.todo.dto.PriorityDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriorityMapper {

    Priority toPriority(PriorityDTO priorityDTO);
    @InheritInverseConfiguration
    PriorityDTO toDTO(Priority priority);
}
