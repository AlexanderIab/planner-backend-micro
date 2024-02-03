package com.iablonski.planner.todo.service.serviceImpl;

import com.iablonski.planner.entity.Task;
import com.iablonski.planner.todo.dto.TaskDTO;
import com.iablonski.planner.todo.repository.TaskRepo;
import com.iablonski.planner.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepo taskRepo;

    @Autowired
    public TaskServiceImpl(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    @Override
    public TaskDTO getTaskById(Long id) {
        Task task = taskRepo.findById(id).orElseThrow();
        return TaskDTO.toDTO(task);
    }

    @Override
    public void createTask(TaskDTO taskDTO) {
        Task task = toTask(taskDTO);
        taskRepo.save(task);
    }


    @Override
    public void updateTask(TaskDTO taskDTO) {
        taskRepo.findById(taskDTO.id()).orElseThrow();
        Task task = toTask(taskDTO);
        task.setId(taskDTO.id());
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
                .map(TaskDTO::toDTO)
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
        return page.map(TaskDTO::toDTO);
    }

    private Task toTask(TaskDTO taskDTO) {
        Task task  = new Task();
        task.setTitle(taskDTO.title());
        task.setCompleted(taskDTO.completed());
        task.setPriority(taskDTO.priority());
        task.setTaskDate(taskDTO.taskDate());
        task.setUserId(taskDTO.userId());
        task.setCategory(taskDTO.category());
        return task;
    }
}