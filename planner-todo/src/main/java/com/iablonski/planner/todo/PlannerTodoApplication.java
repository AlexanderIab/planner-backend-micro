package com.iablonski.planner.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PlannerTodoApplication {


    public static void main(String[] args) {
        SpringApplication.run(PlannerTodoApplication.class, args);
    }

}
