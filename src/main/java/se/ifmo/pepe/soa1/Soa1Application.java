package se.ifmo.pepe.soa1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import se.ifmo.pepe.soa1.configuration.SecurityConfigurationMap;

@SpringBootApplication
@EnableConfigurationProperties(SecurityConfigurationMap.class)
public class Soa1Application {
    public static void main(String[] args) {
        SpringApplication.run(Soa1Application.class, args);
    }
}
