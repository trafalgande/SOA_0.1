package se.ifmo.pepe.soa1.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import se.ifmo.pepe.soa1.exception.resolver.DefaultExceptionHandlerExceptionResolver;

@EnableWebSecurity
@Configuration
public class ExceptionHandlerConfiguration {
    @Bean
    DefaultExceptionHandlerExceptionResolver defaultExceptionHandlerExceptionResolver() {
        return new DefaultExceptionHandlerExceptionResolver();
    }
}
