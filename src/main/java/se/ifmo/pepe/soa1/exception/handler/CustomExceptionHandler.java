package se.ifmo.pepe.soa1.exception.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import se.ifmo.pepe.soa1.exception.ApiError;
import se.ifmo.pepe.soa1.exception.UsernameAlreadyExistsException;
import se.ifmo.pepe.soa1.exception.buildr.ExceptionBuilder;

import java.util.NoSuchElementException;


@RestControllerAdvice
@RequiredArgsConstructor
public class CustomExceptionHandler {

    private final ExceptionBuilder exceptionBuilder;

    @ExceptionHandler(value = BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ApiError handleBadCredentialsException(BadCredentialsException exception,
                                                                   WebRequest request) {
        return exceptionBuilder.build(exception, request);
    }

    @ExceptionHandler(value = UsernameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ApiError handleValidationException(UsernameAlreadyExistsException exception,
                                                               WebRequest request) {
        return exceptionBuilder.build(exception, request);
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ApiError handleNoSuchElementException(NoSuchElementException exception,
                                 WebRequest request) {
        return exceptionBuilder.build(exception, request);
    }
}
