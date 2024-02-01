package com.iablonski.planner.todo.repository;

import com.iablonski.planner.entity.Stat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatRepo extends JpaRepository<Stat, Long> {
    Optional<Stat> findByUserId(Long userId);
}
