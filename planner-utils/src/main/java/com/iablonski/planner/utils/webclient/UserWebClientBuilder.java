package com.iablonski.planner.utils.webclient;

import com.iablonski.planner.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class UserWebClientBuilder {

    private static final String baseUrl = "http://localhost:8765/planner-users/api/user/";

    public boolean userExists(Long userId) {
        User user = WebClient.create(baseUrl)
                .post()
                .uri("id")
                .bodyValue(userId)
                .retrieve()
                .bodyToFlux(User.class)
                .blockFirst();
        return user != null;
    }
}
