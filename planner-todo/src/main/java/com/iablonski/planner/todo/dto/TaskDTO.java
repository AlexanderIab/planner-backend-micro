package com.iablonski.planner.todo.dto;

import com.iablonski.planner.entity.Category;
import com.iablonski.planner.entity.Priority;
import com.iablonski.planner.entity.Task;

import java.util.Date;

public record TaskDTO (
        Long id,
        String title,
        Boolean completed,
        Date taskDate,
        Priority priority,
        Category category,
        Long userId) {

    public TaskDTO(String title, Boolean completed, Date taskDate, Priority priority, Category category, Long userId) {
        this(null, title, completed, taskDate, priority, category, userId);
    }
}