package com.iablonski.planner.todo.dto;


public record CategoryDTO(Long id, String title, Long completedCount, Long uncompletedCount, Long userId) {
    public CategoryDTO(String title, Long userId) {
        this(null, title, null, null, userId);
    }
}