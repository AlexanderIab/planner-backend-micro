package com.iablonski.planner.todo.mapper;

import com.iablonski.planner.entity.Task;
import com.iablonski.planner.todo.dto.TaskDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task toTask(TaskDTO taskDTO);
    @InheritInverseConfiguration
    TaskDTO toDTO(Task task);
}