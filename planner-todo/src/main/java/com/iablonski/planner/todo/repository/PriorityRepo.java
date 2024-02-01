package com.iablonski.planner.todo.repository;

import com.iablonski.planner.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PriorityRepo extends JpaRepository<Priority, Long> {
    List<Priority> findByUserIdOrderByTitleAsc(Long userId);

    @Query("SELECT priority from Priority priority where :title is null or :title='' " +
            "or lower(priority.title) like lower(concat('%',:title,'%') ) " +
            "and priority.userId=:id order by priority.title asc")
    List<Priority> findByTitle(@Param("title") String title, @Param("id") Long userId);
}
