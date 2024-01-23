package com.iablonski.micro.eurekaserver;

import com.netflix.eureka.EurekaServerConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {"server.port=8081",
        "eureka.client.register-with-eureka=false",
        "eureka.client.fetch-registry=false"})
class EurekaServerApplicationTests {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private EurekaServerConfig eurekaServerConfig;

    @Test
    void contextLoads() {

    }
}
