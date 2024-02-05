package com.iablonski.planner.utils.webclient;

import com.iablonski.planner.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class UserWebClientBuilder {
    private static final String baseUrlUser = "http://localhost:8765/planner-users/api/user/";
    private static final String baseUrlData = "http://localhost:8765/planner-todo/api/data/";

    public void userExists(Long userId) {
        WebClient.create(baseUrlUser)
                .post()
                .uri("id")
                .bodyValue(userId)
                .retrieve()
                .bodyToFlux(User.class)
                .blockFirst();
    }

    public Mono<Boolean> initData(Long userId) {
        return WebClient.create(baseUrlData)
                .post()
                .uri("init")
                .bodyValue(userId)
                .retrieve()
                .bodyToMono(Boolean.class);
    }
}
