package se.ifmo.pepe.soa1.api.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import se.ifmo.pepe.soa1.api.exception.custom.UsernameAlreadyExistsException;
import se.ifmo.pepe.soa1.api.exception.resolver.ExceptionBuilder;

import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.*;
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
//        final List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
//        Map<String, Set<String>> errors = fieldErrors.stream().collect(
//                Collectors.groupingBy(FieldError::getField,
//                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toSet())
//                )
//        );

        List<String> errors = new ArrayList<String>();
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : exception.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

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
