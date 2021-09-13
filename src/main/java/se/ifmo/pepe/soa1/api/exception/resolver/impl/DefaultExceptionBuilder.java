package se.ifmo.pepe.soa1.api.exception.resolver.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import se.ifmo.pepe.soa1.api.exception.ApiError;
import se.ifmo.pepe.soa1.api.exception.resolver.ExceptionBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@Profile("!local")
public class DefaultExceptionBuilder implements ExceptionBuilder {
    @Override
    public ResponseEntity<Object> build(RuntimeException exception, WebRequest request, HttpStatus status) {
        return new ResponseEntity<>(ApiError.builder()
                                            .status(status)
                                            .timestamp(LocalDateTime.now())
                                            .message(exception.getLocalizedMessage())
                                            .path(((ServletWebRequest) request).getRequest().getRequestURI())
                                            .build(), status);
    }

    @Override
    public ResponseEntity<Object> build(MethodArgumentNotValidException exception, Map<String, Set<String>> errors, WebRequest request, HttpStatus status) {
        return new ResponseEntity<>(ApiError.builder()
                                            .status(status)
                                            .timestamp(LocalDateTime.now())
                                            .message(exception.getMessage())
                                            .errorMap(errors)
                                            .path(((ServletWebRequest) request).getRequest().getRequestURI())
                                            .build(), status);
    }

    @Override
    public ResponseEntity<Object> build(MethodArgumentNotValidException exception, List<String> errors, WebRequest request, HttpStatus status) {
        return new ResponseEntity<>(ApiError.builder()
                                            .status(status)
                                            .timestamp(LocalDateTime.now())
                                            .errors(errors)
                                            .path(((ServletWebRequest) request).getRequest().getRequestURI())
                                            .build(), status);
    }
}