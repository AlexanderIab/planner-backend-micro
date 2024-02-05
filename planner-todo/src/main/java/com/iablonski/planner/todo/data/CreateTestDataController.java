package com.iablonski.planner.todo.data;

import com.iablonski.planner.entity.Category;
import com.iablonski.planner.entity.Priority;
import com.iablonski.planner.todo.dto.CategoryDTO;
import com.iablonski.planner.todo.dto.PriorityDTO;
import com.iablonski.planner.todo.dto.TaskDTO;
import com.iablonski.planner.todo.mapper.CategoryMapper;
import com.iablonski.planner.todo.mapper.PriorityMapper;
import com.iablonski.planner.todo.repository.CategoryRepo;
import com.iablonski.planner.todo.repository.PriorityRepo;
import com.iablonski.planner.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/api/data")
public class CreateTestDataController {

    private final TaskService taskService;
    private final PriorityRepo priorityRepo;
    private final CategoryRepo categoryRepo;
    private final CategoryMapper categoryMapper;
    private final PriorityMapper priorityMapper;

    @Autowired
    public CreateTestDataController(TaskService taskService,
                                    PriorityRepo priorityRepo,
                                    CategoryRepo categoryRepo,
                                    CategoryMapper categoryMapper,
                                    PriorityMapper priorityMapper) {
        this.taskService = taskService;
        this.priorityRepo = priorityRepo;
        this.categoryRepo = categoryRepo;
        this.categoryMapper = categoryMapper;
        this.priorityMapper = priorityMapper;
    }

    @PostMapping("/init")
    public ResponseEntity<Boolean> init(@RequestBody Long userId){

        Category category1 = categoryRepo.save(categoryMapper.toCategory(new CategoryDTO("Work", userId)));
        Category category2 = categoryRepo.save(categoryMapper.toCategory(new CategoryDTO("Family", userId)));
        Category category3 = categoryRepo.save(categoryMapper.toCategory(new CategoryDTO("Hobby", userId)));

        Priority priority1 = priorityRepo.save(priorityMapper.toPriority(new PriorityDTO("High", "#FF0000", userId)));
        Priority priority2 = priorityRepo.save(priorityMapper.toPriority(new PriorityDTO("Middle", "#FFFF00", userId)));
        Priority priority3 = priorityRepo.save(priorityMapper.toPriority(new PriorityDTO("Low", "#00FF00", userId)));


        Date tomorrow = new Date();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(tomorrow);
        calendar1.add(Calendar.DATE, 1);
        tomorrow = calendar1.getTime();

        Date oneWeek = new Date();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(oneWeek);
        calendar2.add(Calendar.DATE, 7);
        oneWeek = calendar2.getTime();

        Date oneMonth = new Date();
        Calendar calendar3 = Calendar.getInstance();
        calendar3.setTime(oneMonth);
        calendar3.add(Calendar.DATE, 30);
        oneMonth = calendar3.getTime();

        TaskDTO task1 = new TaskDTO(
                "To Do Reports",
                true,
                oneMonth,
                priority1,
                category1,
                userId);
        TaskDTO task2 = new TaskDTO(
                "Walk with friends",
                false,
                tomorrow,
                priority2,
                category2,
                userId);
        TaskDTO task3 = new TaskDTO(
                "Playing guitar",
                false,
                oneWeek,
                priority3,
                category3,
                userId);



        taskService.createTask(task1);
        taskService.createTask(task2);
        taskService.createTask(task3);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}