package se.ifmo.pepe.soa1.dto.response;

import lombok.Data;

@Data
public class AuthUserView {
    private String username;
    private String email;
    private String token;
}
