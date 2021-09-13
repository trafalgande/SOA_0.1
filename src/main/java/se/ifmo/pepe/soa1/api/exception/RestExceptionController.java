package se.ifmo.pepe.soa1.api.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import se.ifmo.pepe.soa1.api.exception.custom.UsernameAlreadyExistsException;
import se.ifmo.pepe.soa1.api.exception.resolver.ExceptionBuilder;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@RestControllerAdvice
@RequiredArgsConstructor
public class RestExceptionController extends ResponseEntityExceptionHandler {

    private final ExceptionBuilder exceptionBuilder;

    @ExceptionHandler(value = NoSuchElementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleNoSuchElementException(RuntimeException exception,
                                                                  WebRequest request) {
        return exceptionBuilder.build(exception, request, HttpStatus.BAD_REQUEST);
    }

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        List<String> errors = exception.getBindingResult().getFieldErrors()
                                       .stream()
                                       .map(error -> error.getField() + ": " + error.getDefaultMessage())
                                       .collect(Collectors.toList());
        exception.getBindingResult().getGlobalErrors()
                 .stream()
                 .map(error -> error.getObjectName() + ": " + error.getDefaultMessage())
                 .forEach(errors::add);

        return exceptionBuilder.build(exception, errors, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException exception,
                                                                   WebRequest request) {
        return exceptionBuilder.build(exception, request, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = UsernameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleValidationException(RuntimeException exception,
                                                               WebRequest request) {
        return exceptionBuilder.build(exception, request, HttpStatus.BAD_REQUEST);
    }
}
