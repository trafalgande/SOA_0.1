package se.ifmo.pepe.soa1.exception.resolver;

import lombok.SneakyThrows;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultExceptionHandlerExceptionResolver implements Ordered, HandlerExceptionResolver {
    @SneakyThrows
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object handler, Exception ex) {
        response.sendError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal server error has occurred."
        );
        return new ModelAndView();
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}