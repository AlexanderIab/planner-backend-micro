package com.iablonski.planner.utils.webclient;

import com.iablonski.planner.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class UserWebClientBuilder {

    private static final String baseUrl = "http://localhost:8765/planner-users/api/user/";

    public Mono<User> userExists(Long userId) {
        return WebClient.create(baseUrl)
                .post()
                .uri("id")
                .bodyValue(userId)
                .retrieve()
                .bodyToMono(User.class);
    }
}
