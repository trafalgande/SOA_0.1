package se.ifmo.pepe.soa1.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AuthUserRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
