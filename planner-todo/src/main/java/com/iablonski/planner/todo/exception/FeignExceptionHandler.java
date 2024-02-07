package com.iablonski.planner.todo.exception;

import com.google.common.io.CharStreams;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.antlr.v4.runtime.CharStream;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

@Component
public class FeignExceptionHandler implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        return switch (response.status()) {
            case 406 -> new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, readMessage(response));
            default -> null;
        };
    }

    private String readMessage(Response response) {
        try (Reader reader = response.body().asReader(Charset.defaultCharset())) {
            return CharStreams.toString(reader);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении сообщения из ответа", e);
        }
    }
}
