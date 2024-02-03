package com.iablonski.planner.todo.service;

import com.iablonski.planner.todo.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO getCategoryById(Long id);
    void createCategory(CategoryDTO categoryDTO);
    void updateCategory(CategoryDTO categoryDTO);
    void deleteCategoryById(Long id);
    List<CategoryDTO> getCategoriesByUserId(Long userId);
    List<CategoryDTO> findByTitle(String title, Long userId);
}
