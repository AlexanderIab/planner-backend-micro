package com.iablonski.planner.users.controller;

import com.iablonski.planner.entity.User;
import com.iablonski.planner.users.dto.UserDTO;
import com.iablonski.planner.users.response.MessageResponse;
import com.iablonski.planner.users.search.UserSearchValues;
import com.iablonski.planner.users.service.UserService;
import com.iablonski.planner.utils.webclient.UserWebClientBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    public static final String ID_COLUMN = "id";
    private final UserService userService;
    private final UserWebClientBuilder userWebClientBuilder;

    @Autowired
    public UserController(UserService userService, UserWebClientBuilder userWebClientBuilder) {
        this.userService = userService;
        this.userWebClientBuilder = userWebClientBuilder;
    }

    @PostMapping("/email")
    public ResponseEntity<UserDTO> getUserByEmail(@RequestBody String email) {
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
    }

    @PostMapping("/id")
    public ResponseEntity<UserDTO> getUserById(@RequestBody Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> createUser(@RequestBody UserDTO userDTO) {
        User user = userService.createUser(userDTO);
        if (user != null) {
            userWebClientBuilder.initData(user.getId()).subscribe(result -> System.out.println("result: " + result));
            return new ResponseEntity<>(new MessageResponse("Successfully created"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MessageResponse("User and data not created"), HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping("/update")
    public ResponseEntity<MessageResponse> updateUser(@RequestBody UserDTO userDTO) {
        userService.updateUser(userDTO);
        return new ResponseEntity<>(new MessageResponse("Successfully updated"), HttpStatus.OK);
    }

    @PostMapping("/delete/email")
    public ResponseEntity<MessageResponse> deleteUserByEmail(@RequestBody String email) {
        userService.deleteUserByEmail(email);
        return new ResponseEntity<>(new MessageResponse("Successfully deleted"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(new MessageResponse("Successfully deleted"), HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<UserDTO>> searchUser(@RequestBody UserSearchValues values) {
        Sort sort = Sort.by(values.getSortDirection(), values.sortColumn(), ID_COLUMN);
        PageRequest pageRequest = PageRequest.of(values.pageNumber(), values.pageSize(), sort);
        Page<UserDTO> page = userService.findByParams(values.email(), values.username(), pageRequest);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
}