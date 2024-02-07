package com.iablonski.planner.todo.feign;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserFeignClientFallback implements UserFeignClient{
    @Override
    public ResponseEntity<Object> getUserById(Long userId) {
        return null;
    }
}
