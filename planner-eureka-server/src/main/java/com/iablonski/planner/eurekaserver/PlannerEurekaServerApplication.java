package com.iablonski.planner.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class PlannerEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlannerEurekaServerApplication.class, args);
    }

}