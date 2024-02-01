package com.iablonski.planner.todo.service;

import com.iablonski.planner.todo.dto.TaskDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

public interface TaskService {

    TaskDTO getTaskById(Long id);

    List<TaskDTO> getTasksByUserId(Long userId);

    void createTask(TaskDTO taskDTO);

    void updateTask(TaskDTO taskDTO);

    void deleteTaskById(Long id);

    Page<TaskDTO> findByParams(String title,
                            Boolean completed,
                            Long userId,
                            Long priorityId,
                            Long categoryId,
                            Date dateFrom,
                            Date dateTo,
                            PageRequest pageRequest);
}