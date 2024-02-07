package com.iablonski.planner.todo.controller;

import com.iablonski.planner.todo.dto.CategoryDTO;
import com.iablonski.planner.todo.feign.UserFeignClient;
import com.iablonski.planner.todo.payload.response.MessageResponse;
import com.iablonski.planner.todo.search.CategorySearchValues;
import com.iablonski.planner.todo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
@CrossOrigin
public class CategoryController {

    private final CategoryService categoryService;
    private final UserFeignClient userFeignClient;
//    private final ResponseErrorMessage errorMessage;

    @Autowired
    public CategoryController(CategoryService categoryService, @Qualifier("com.iablonski.planner.todo.feign.UserFeignClient") UserFeignClient userFeignClient /*ResponseErrorMessage errorMessage*/) {
        this.categoryService = categoryService;
        /*        this.errorMessage = errorMessage;*/
        this.userFeignClient = userFeignClient;
    }

    @PostMapping("/id")
    public ResponseEntity<Object> getCategoryById(@RequestBody Long id, BindingResult result) {
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
    }

    @PostMapping("/all")
    public ResponseEntity<List<CategoryDTO>> getCategoriesByUserEmail(@RequestBody Long id) {
        List<CategoryDTO> categories = categoryService.getCategoriesByUserId(id);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> createCategory(@RequestBody CategoryDTO categoryDTO) {
        ResponseEntity<Object> user = userFeignClient.getUserById(categoryDTO.userId());
        if (user == null) {
            return new ResponseEntity<>(new MessageResponse("User service not working"), HttpStatus.NOT_FOUND);
        }
        if (user.getBody() != null) {
            categoryService.createCategory(categoryDTO);
            return new ResponseEntity<>(new MessageResponse("Successfully created"), HttpStatus.OK);
        }
        else return new ResponseEntity<>(new MessageResponse("Not created"), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update")
    public ResponseEntity<MessageResponse> updateCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryService.updateCategory(categoryDTO);
        return new ResponseEntity<>(new MessageResponse("Successfully updated"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<>(new MessageResponse("Successfully deleted"), HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<CategoryDTO>> searchCategory(@RequestBody CategorySearchValues values) {
        List<CategoryDTO> categories = categoryService.findByTitle(values.title(), values.userId());
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}