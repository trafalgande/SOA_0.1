package se.ifmo.pepe.soa1.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.ifmo.pepe.soa1.domain.User;
import se.ifmo.pepe.soa1.dto.mapper.UserMapper;
import se.ifmo.pepe.soa1.dto.request.AuthUserRequest;
import se.ifmo.pepe.soa1.dto.request.CreateUserRequest;
import se.ifmo.pepe.soa1.dto.response.AuthUserView;
import se.ifmo.pepe.soa1.dto.response.UserView;
import se.ifmo.pepe.soa1.exception.UsernameAlreadyExistsException;
import se.ifmo.pepe.soa1.repository.UserRepository;
import se.ifmo.pepe.soa1.security.JwtTokenUtil;
import se.ifmo.pepe.soa1.service.UserService;

import java.util.HashSet;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final JwtTokenUtil jwtTokenUtil;


    @Override
    public UserView register(CreateUserRequest createUserRequest) {
        if (usernameExists(createUserRequest.getUsername()))
            throw new UsernameAlreadyExistsException(String.format("Username '%s' is already taken", createUserRequest.getUsername()));
        if (createUserRequest.getAuthorities() == null || createUserRequest.getAuthorities().isEmpty())
            createUserRequest.setAuthorities(new HashSet<>());

        User user = userMapper.create(createUserRequest);
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        user = userRepository.save(user);

        return userMapper.toUserView(user);
    }

    @Override
    public AuthUserView authenticate(AuthUserRequest authUserRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authUserRequest.getUsername(),
                        authUserRequest.getPassword()));

        User user = (User) authentication.getPrincipal();
        String token = jwtTokenUtil.generateAccessToken(user);
        AuthUserView authUserView = userMapper.toAuthUserView(user);
        authUserView.setToken(token);
        return authUserView;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(String.format("User [%s], not found", username))
                );
    }

    private boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    private boolean userExists(UUID uuid) {
        return userRepository.existsById(uuid);
    }
}
