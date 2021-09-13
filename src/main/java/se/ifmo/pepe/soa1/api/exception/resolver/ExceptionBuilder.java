package se.ifmo.pepe.soa1.api.exception.resolver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ExceptionBuilder {
    ResponseEntity<Object> build(RuntimeException exception, WebRequest request, HttpStatus status);
    ResponseEntity<Object> build(MethodArgumentNotValidException exception, Map<String, Set<String>> errors, WebRequest request, HttpStatus status);
    ResponseEntity<Object> build(MethodArgumentNotValidException exception, List<String> errors, WebRequest request, HttpStatus status);
}
