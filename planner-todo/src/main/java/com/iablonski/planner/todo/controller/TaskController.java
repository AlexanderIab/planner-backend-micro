package com.iablonski.planner.todo.controller;

import com.iablonski.planner.todo.dto.TaskDTO;
import com.iablonski.planner.todo.search.TaskSearchValues;
import com.iablonski.planner.todo.service.TaskService;
import com.iablonski.planner.todo.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    public static final String ID_COLUMN = "id";
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/id")
    public ResponseEntity<TaskDTO> getTaskById(@RequestBody Long id){
        return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
    }

    @PostMapping("/all")
    public ResponseEntity<List<TaskDTO>> getTasksByUserEmail(@RequestBody Long userId){
        List<TaskDTO> tasks = taskService.getTasksByUserId(userId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> createTask(@RequestBody TaskDTO taskDTO){
        taskService.createTask(taskDTO);
        return new ResponseEntity<>(new MessageResponse("Successfully created"), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<MessageResponse> updateTask(@RequestBody TaskDTO taskDTO){
        taskService.updateTask(taskDTO);
        return new ResponseEntity<>(new MessageResponse("Successfully updated"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> deleteTask(@PathVariable("id") Long id){
        taskService.deleteTaskById(id);
        return new ResponseEntity<>(new MessageResponse("Successfully deleted"), HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<TaskDTO>> searchTask(@RequestBody TaskSearchValues values){
        Sort sort = Sort.by(values.getSortDirection(), values.sortColumn(), ID_COLUMN);
        PageRequest pageRequest = PageRequest.of(values.pageNumber(), values.pageSize(), sort);
        Page<TaskDTO> taskList = taskService
                .findByParams(values.title(),
                        values.getCompleted(),
                        values.userId(),
                        values.priorityId(),
                        values.categoryId(),
                        values.getDateFrom(),
                        values.getDateTo(),
                        pageRequest);
        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }
}