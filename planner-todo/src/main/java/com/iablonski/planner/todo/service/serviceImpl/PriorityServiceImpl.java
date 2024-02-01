package com.iablonski.planner.todo.service.serviceImpl;

import com.iablonski.planner.entity.Priority;
import com.iablonski.planner.todo.dto.PriorityDTO;
import com.iablonski.planner.todo.repository.PriorityRepo;
import com.iablonski.planner.todo.service.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PriorityServiceImpl implements PriorityService {

    private final PriorityRepo priorityRepo;

    @Autowired
    public PriorityServiceImpl(PriorityRepo priorityRepo) {
        this.priorityRepo = priorityRepo;
    }


    @Override
    public PriorityDTO getPriorityById(Long id) {
        Priority priority = priorityRepo.findById(id).orElseThrow();
        return PriorityDTO.toDTO(priority);
    }

    @Override
    public void createPriority(PriorityDTO priorityDTO) {
        Priority priority = new Priority();
        priority.setTitle(priorityDTO.title());
        priority.setColor(priorityDTO.color());
        priority.setUserId(priorityDTO.userId());
        priorityRepo.save(priority);
    }

    @Override
    public void updatePriority(PriorityDTO priorityDTO) {
        Priority priority = priorityRepo.findById(priorityDTO.id()).orElseThrow();
        priority.setTitle(priorityDTO.title());
        priority.setColor(priorityDTO.color());
        priority.setUserId(priorityDTO.userId());
        priorityRepo.save(priority);
    }

    @Override
    public void deletePriorityById(Long id) {
        Optional<Priority> priority = priorityRepo.findById(id);
        priority.ifPresent(priorityRepo::delete);
    }

    @Override
    public List<PriorityDTO> getPrioritiesByUserId(Long userId) {
        return priorityRepo.findByUserIdOrderByTitleAsc(userId).stream()
                .map(PriorityDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PriorityDTO> findByTitle(String title, Long userId) {
        return priorityRepo.findByTitle(title, userId).stream()
                .map(PriorityDTO::toDTO)
                .collect(Collectors.toList());
    }
}