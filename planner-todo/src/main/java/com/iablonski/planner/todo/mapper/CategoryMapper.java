package com.iablonski.planner.todo.mapper;

import com.iablonski.planner.entity.Category;
import com.iablonski.planner.todo.dto.CategoryDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toCategory(CategoryDTO categoryDTO);
    @InheritInverseConfiguration
    CategoryDTO toDTO(Category category);
}
