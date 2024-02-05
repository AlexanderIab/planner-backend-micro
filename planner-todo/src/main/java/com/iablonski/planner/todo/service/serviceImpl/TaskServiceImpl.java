package com.iablonski.planner.todo.service.serviceImpl;

import com.iablonski.planner.entity.Task;
import com.iablonski.planner.todo.dto.TaskDTO;
import com.iablonski.planner.todo.mapper.TaskMapper;
import com.iablonski.planner.todo.repository.TaskRepo;
import com.iablonski.planner.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepo taskRepo;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskServiceImpl(TaskRepo taskRepo, TaskMapper taskMapper) {
        this.taskRepo = taskRepo;
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskDTO getTaskById(Long id) {
        Task task = taskRepo.findById(id).orElseThrow();
        return taskMapper.toDTO(task);
    }

    @Override
    public void createTask(TaskDTO taskDTO) {
        Task task = taskMapper.toTask(taskDTO);
        task.setId(null);
        taskRepo.save(task);
    }

    @Override
    public void updateTask(TaskDTO taskDTO) {
        taskRepo.findById(taskDTO.id()).orElseThrow();
        Task task = taskMapper.toTask(taskDTO);
        taskRepo.save(task);
    }

    @Override
    public void deleteTaskById(Long id) {
        Optional<Task> task = taskRepo.findById(id);
        task.ifPresent(taskRepo::delete);
    }

    @Override
    public List<TaskDTO> getTasksByUserId(Long userId) {
        return taskRepo.findByUserIdOrderByTitleAsc(userId).stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<TaskDTO> findByParams(String title, Boolean completed,
                                      Long userId, Long priorityId,
                                      Long categoryId, Date dateFrom,
                                      Date dateTo, PageRequest pageRequest) {
        Page<Task> page = taskRepo.findTaskByParams(
                title, completed, userId, priorityId,
                categoryId, dateFrom, dateTo, pageRequest);
        return page.map(taskMapper::toDTO);
    }
}