package com.iablonski.planner.todo.service;

import com.iablonski.planner.todo.dto.StatDTO;

public interface StatService {
    StatDTO findStat(Long userId);
}
