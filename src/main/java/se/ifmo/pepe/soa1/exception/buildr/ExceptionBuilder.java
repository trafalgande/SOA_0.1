package se.ifmo.pepe.soa1.exception.buildr;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import se.ifmo.pepe.soa1.exception.ApiError;

import java.util.List;

public interface ExceptionBuilder {
    ApiError build(RuntimeException exception, WebRequest request);
    ApiError build(MethodArgumentNotValidException exception, List<String> errors, WebRequest request);
}
