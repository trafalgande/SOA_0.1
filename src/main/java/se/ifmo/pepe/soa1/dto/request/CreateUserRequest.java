package se.ifmo.pepe.soa1.dto.request;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class CreateUserRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
    private Set<String> authorities;
}