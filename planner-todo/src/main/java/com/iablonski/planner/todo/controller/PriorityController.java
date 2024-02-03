package com.iablonski.planner.todo.controller;

import com.iablonski.planner.todo.dto.PriorityDTO;
import com.iablonski.planner.todo.payload.response.MessageResponse;
import com.iablonski.planner.todo.search.PrioritySearchValues;
import com.iablonski.planner.todo.service.PriorityService;
import com.iablonski.planner.utils.webclient.UserWebClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/priority")
public class PriorityController {

    private final PriorityService priorityService;
    private final UserWebClientBuilder userWebClientBuilder;

    @Autowired
    public PriorityController(PriorityService priorityService, UserWebClientBuilder userWebClientBuilder) {
        this.priorityService = priorityService;
        this.userWebClientBuilder = userWebClientBuilder;
    }

    @PostMapping("/id")
    public ResponseEntity<PriorityDTO> getPriorityById(@RequestBody Long id){
        return new ResponseEntity<>(priorityService.getPriorityById(id), HttpStatus.OK);
    }

    @PostMapping("/all")
    public ResponseEntity<List<PriorityDTO>> getPrioritiesByUserEmail(@RequestBody Long userId){
        List<PriorityDTO> priorities = priorityService.getPrioritiesByUserId(userId);
        return new ResponseEntity<>(priorities, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> createPriority(@RequestBody PriorityDTO priorityDTO){
        // Since it is important for us to receive the response and
        // then execute the next method, an asynchronous call is not suitable here,
        // so this is only a demonstration
        userWebClientBuilder.userExists(priorityDTO.userId()).subscribe(user -> System.out.println("user = " + user.getUsername()));

        priorityService.createPriority(priorityDTO);
        return new ResponseEntity<>(new MessageResponse("Successfully created"), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<MessageResponse> updatePriority(@RequestBody PriorityDTO priorityDTO){
        userWebClientBuilder.userExists(priorityDTO.userId());
        priorityService.updatePriority(priorityDTO);
        return new ResponseEntity<>(new MessageResponse("Successfully updated"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> deletePriority (@PathVariable("id") Long id){
        priorityService.deletePriorityById(id);
        return new ResponseEntity<>(new MessageResponse("Successfully deleted"), HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<PriorityDTO>> searchPriority(@RequestBody PrioritySearchValues values){
        List<PriorityDTO> priorities = priorityService.findByTitle(values.title(), values.userId());
        return new ResponseEntity<>(priorities, HttpStatus.OK);
    }
}