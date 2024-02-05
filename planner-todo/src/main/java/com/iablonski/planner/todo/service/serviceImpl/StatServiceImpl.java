package com.iablonski.planner.todo.service.serviceImpl;

import com.iablonski.planner.entity.Stat;
import com.iablonski.planner.todo.dto.StatDTO;
import com.iablonski.planner.todo.mapper.StatMapper;
import com.iablonski.planner.todo.repository.StatRepo;
import com.iablonski.planner.todo.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatServiceImpl implements StatService {

    private final StatRepo statRepo;
    private final StatMapper statMapper;

    @Autowired
    public StatServiceImpl(StatRepo statRepo, StatMapper statMapper) {
        this.statRepo = statRepo;
        this.statMapper = statMapper;
    }

    @Override
    public StatDTO findStat(Long userId) {
        Stat stat = statRepo.findByUserId(userId).orElseThrow();
        return statMapper.toDTO(stat);
    }
}