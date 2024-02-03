package com.iablonski.planner.todo.repository;

import com.iablonski.planner.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    List<Category> findByUserIdOrderByTitleAsc(Long userId);

    @Query("SELECT category from Category category where :title is null or :title='' " +
            "or lower(category.title) like lower(concat('%',:title,'%') ) " +
            "and category.userId=:id order by category.title asc")
    List<Category> findByTitle(@Param("title") String title, @Param("id") Long userId);
}