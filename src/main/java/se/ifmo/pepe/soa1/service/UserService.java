package se.ifmo.pepe.soa1.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import se.ifmo.pepe.soa1.dto.request.AuthUserRequest;
import se.ifmo.pepe.soa1.dto.request.CreateUserRequest;
import se.ifmo.pepe.soa1.dto.response.AuthUserView;
import se.ifmo.pepe.soa1.dto.response.UserView;

public interface UserService extends UserDetailsService {
    ResponseEntity<UserView> create(CreateUserRequest request);

    ResponseEntity<AuthUserView> authenticate(AuthUserRequest request);
}
