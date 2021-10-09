package se.ifmo.pepe.soa1.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("security")
@Data
public class SecurityConfigurationMap {
    public String secret;
}
