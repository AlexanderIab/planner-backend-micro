package com.iablonski.planner.todo.dto;

import com.iablonski.planner.entity.Stat;

public record StatDTO (Long id, Long completedTotal, Long uncompletedTotal, Long userId) {

    public static StatDTO todo(Stat stat){
        return new StatDTO(
                stat.getId(),
                stat.getCompletedTotal(),
                stat.getUncompletedTotal(),
                stat.getUserId());
    }
}