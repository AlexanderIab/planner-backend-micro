package com.iablonski.githubconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class GithubConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GithubConfigServerApplication.class, args);
    }

}
