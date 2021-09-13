package se.ifmo.pepe.soa1.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import se.ifmo.pepe.soa1.dto.request.AuthUserRequest;
import se.ifmo.pepe.soa1.dto.request.CreateUserRequest;
import se.ifmo.pepe.soa1.dto.response.AuthUserView;
import se.ifmo.pepe.soa1.dto.response.UserView;

public interface UserService extends UserDetailsService {
    UserView register(CreateUserRequest request);

    AuthUserView authenticate(AuthUserRequest request);
}
