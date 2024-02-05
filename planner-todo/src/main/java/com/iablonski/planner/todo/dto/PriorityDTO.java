package com.iablonski.planner.todo.dto;

public record PriorityDTO(Long id, String title, String color, Long userId) {
    public PriorityDTO(String title, String color, Long userId) {
        this(null, title, color, userId);
    }
}