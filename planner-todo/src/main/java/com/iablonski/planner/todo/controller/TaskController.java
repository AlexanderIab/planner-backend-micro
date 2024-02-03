package com.iablonski.planner.todo.controller;

import com.iablonski.planner.todo.dto.TaskDTO;
import com.iablonski.planner.todo.search.TaskSearchValues;
import com.iablonski.planner.todo.service.TaskService;
import com.iablonski.planner.todo.payload.response.MessageResponse;
import com.iablonski.planner.utils.webclient.UserWebClientBuilder;
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

    private final UserWebClientBuilder userWebClientBuilder;

    @Autowired
    public TaskController(TaskService taskService, UserWebClientBuilder userWebClientBuilder) {
        this.taskService = taskService;
        this.userWebClientBuilder = userWebClientBuilder;
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
        // Since it is important for us to receive the response and
        // then execute the next method, an asynchronous call is not suitable here,
        // so this is only a demonstration
        userWebClientBuilder.userExists(taskDTO.userId()).subscribe(user -> System.out.println("user = " + user.getUsername()));

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