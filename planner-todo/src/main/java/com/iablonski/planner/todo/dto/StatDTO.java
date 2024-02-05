package com.iablonski.planner.todo.dto;

public record StatDTO (Long id, Long completedTotal, Long uncompletedTotal, Long userId) {
}