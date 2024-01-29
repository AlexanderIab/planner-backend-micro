package com.iablonski.planner.githubconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class PlannerGithubConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlannerGithubConfigServerApplication.class, args);
    }

}
