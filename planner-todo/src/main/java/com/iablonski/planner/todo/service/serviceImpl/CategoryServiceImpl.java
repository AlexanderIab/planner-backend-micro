package com.iablonski.planner.todo.service.serviceImpl;

import com.iablonski.planner.entity.Category;
import com.iablonski.planner.todo.dto.CategoryDTO;
import com.iablonski.planner.todo.mapper.CategoryMapper;
import com.iablonski.planner.todo.repository.CategoryRepo;
import com.iablonski.planner.todo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo, CategoryMapper categoryMapper) {
        this.categoryRepo = categoryRepo;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepo.findById(id).orElseThrow();
        return categoryMapper.toDTO(category);
    }

    @Override
    public void createCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setTitle(categoryDTO.title());
        category.setUserId(categoryDTO.userId());
        categoryRepo.save(category);
    }

    @Override
    public void updateCategory(CategoryDTO categoryDTO) {
        Category category = categoryRepo.findById(categoryDTO.id()).orElseThrow();
        category.setTitle(categoryDTO.title());
        category.setUserId(categoryDTO.userId());
        categoryRepo.save(category);
    }

    @Override
    public void deleteCategoryById(Long id) {
        Optional<Category> category = categoryRepo.findById(id);
        category.ifPresent(categoryRepo::delete);
    }

    @Override
    public List<CategoryDTO> getCategoriesByUserId(Long userId) {
        return categoryRepo.findByUserIdOrderByTitleAsc(userId).stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> findByTitle(String title, Long userId) {
        return categoryRepo.findByTitle(title, userId).stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }
}