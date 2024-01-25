package com.iablonski.micro.eurekaserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations = "classpath:test.yml")
class EurekaServerApplicationTests {

    @LocalServerPort
    private int serverPort;

    @Autowired
    private EurekaServerApplication eurekaServerApplication;

    @Value("${eureka.client.service-url.defaultZone}")
    private String eurekaDefaultZone;

    @Test
    void contextLoads() {
        // Проверяем, что порт сервера установлен как ожидалось
        assertEquals(8761, serverPort);
        assertEquals("http://localhost:8081/eureka", eurekaDefaultZone);
        assertNotNull(eurekaServerApplication, "Контекст приложения не был загружен");


// Проверяем, что Eureka-сервер отвечает на запросы
//        RestTemplate restTemplate = new RestTemplate();
//        String healthEndpoint = "http://localhost:" + serverPort + "/actuator/health";
//        String healthResponse = restTemplate.getForObject(healthEndpoint, String.class);
//        assertTrue(healthResponse.contains("UP")); // Пример проверки, что состояние здоровья - "UP"
    }
}
