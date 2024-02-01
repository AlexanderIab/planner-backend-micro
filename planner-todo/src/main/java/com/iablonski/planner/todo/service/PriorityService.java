package com.iablonski.planner.todo.service;

import com.iablonski.planner.todo.dto.PriorityDTO;

import java.util.List;

public interface PriorityService {
    PriorityDTO getPriorityById(Long id);
    void createPriority(PriorityDTO priorityDTO);
    void updatePriority(PriorityDTO priorityDTO);
    void deletePriorityById(Long id);
    List<PriorityDTO> getPrioritiesByUserId(Long userId);
    List<PriorityDTO> findByTitle(String title, Long userId);
}
