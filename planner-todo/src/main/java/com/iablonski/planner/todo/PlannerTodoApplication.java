package com.iablonski.planner.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.iablonski.planner"})
@EnableJpaRepositories(basePackages = {"com.iablonski.planner.todo"})
@EnableFeignClients
public class PlannerTodoApplication {
    public static void main(String[] args) {
        SpringApplication.run(PlannerTodoApplication.class, args);
    }

}
