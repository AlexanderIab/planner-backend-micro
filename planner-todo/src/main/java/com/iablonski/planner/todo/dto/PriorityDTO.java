package com.iablonski.planner.todo.dto;

import com.iablonski.planner.entity.Priority;

public record PriorityDTO(Long id, String title, String color, Long userId) {
    public static PriorityDTO toDTO(Priority priority){
        return new PriorityDTO(
                priority.getId(),
                priority.getTitle(),
                priority.getColor(),
                priority.getUserId());
    }
}