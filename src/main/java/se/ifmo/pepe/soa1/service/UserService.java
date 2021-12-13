package se.ifmo.pepe.soa1.service;

import dto.music_band.request.AuthUserRequest;
import dto.music_band.request.CreateUserRequest;
import dto.music_band.response.AuthUserView;
import dto.music_band.response.UserView;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserView register(CreateUserRequest request);

    AuthUserView authenticate(AuthUserRequest request);
}
