package com.iablonski.planner.todo.repository;

import com.iablonski.planner.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Long> {

    List<Task> findByUserIdOrderByTitleAsc(Long userId);

    @Query("SELECT task from Task task where " +
            "(:title is  null or :title='' or lower(task.title) like lower(concat('%', :title, '%') ) ) and " +
            "(:completed is null or task.completed=:completed) and " +
            "(:priorityId is null or task.priority.id=:priorityId) and" +
            "(:categoryId is null or task.category.id=:categoryId) and" +
            "((cast(:dateFrom as TIMESTAMP) is null or task.taskDate>=:dateFrom) and" +
            "(cast(:dateTo as TIMESTAMP) is null or task.taskDate<=:dateTo)) and " +
            "(task.userId=:id)")
    Page<Task> findTaskByParams(@Param("title") String title,
                                @Param("completed") Boolean completed,
                                @Param("id") Long userId,
                                @Param("priorityId") Long priorityId,
                                @Param("categoryId") Long categoryId,
                                @Param("dateFrom") Date dateFrom,
                                @Param("dateTo") Date dateTo,
                                Pageable pageable);
}
