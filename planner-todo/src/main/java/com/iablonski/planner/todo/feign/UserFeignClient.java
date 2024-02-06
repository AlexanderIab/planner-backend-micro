package com.iablonski.planner.todo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "planner-users")
public interface UserFeignClient {
    @PostMapping("/api/user/id")
    ResponseEntity<Object> getUserById(@RequestBody Long userId);
}
