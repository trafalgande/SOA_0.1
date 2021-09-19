package se.ifmo.pepe.soa1.exception.buildr.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import se.ifmo.pepe.soa1.exception.ApiError;
import se.ifmo.pepe.soa1.exception.buildr.ExceptionBuilder;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
@Profile("local")
public class LocalExceptionBuilder implements ExceptionBuilder {
    @Override
    public ApiError build(RuntimeException exception, WebRequest request) {
        return ApiError.builder()
                .timestamp(LocalDateTime.now())
                .message(exception.getLocalizedMessage())
                .details(Arrays.toString(exception.getStackTrace())) // debug-only
                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                .build();
    }

    @Override
    public ApiError build(MethodArgumentNotValidException exception, List<String> errors, WebRequest request) {
        return ApiError.builder()
                .timestamp(LocalDateTime.now())
                .message(errors.toString())
                .details(Arrays.toString(exception.getStackTrace())) // debug-only
                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                .build();
    }
}