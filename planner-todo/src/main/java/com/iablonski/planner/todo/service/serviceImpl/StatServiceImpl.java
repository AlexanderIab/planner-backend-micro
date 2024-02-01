package com.iablonski.planner.todo.service.serviceImpl;

import com.iablonski.planner.entity.Stat;
import com.iablonski.planner.todo.dto.StatDTO;
import com.iablonski.planner.todo.repository.StatRepo;
import com.iablonski.planner.todo.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatServiceImpl implements StatService {

    private final StatRepo statRepo;

    @Autowired
    public StatServiceImpl(StatRepo statRepo) {
        this.statRepo = statRepo;
    }

    @Override
    public StatDTO findStat(Long userId) {
        Stat stat = statRepo.findByUserId(userId).orElseThrow();
        return StatDTO.todo(stat);
    }
}