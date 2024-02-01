package com.iablonski.planner.todo.dto;

import com.iablonski.planner.entity.Category;

public record CategoryDTO(Long id, String title, Long completedCount, Long uncompletedCount, Long userId) {

    public static CategoryDTO toDTO(Category category){
        return new CategoryDTO(
                category.getId(),
                category.getTitle(),
                category.getCompletedCount(),
                category.getUncompletedCount(),
                category.getUserId());
    }
}