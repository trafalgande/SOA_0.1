package se.ifmo.pepe.soa1.api;

import dto.music_band.request.AuthUserRequest;
import dto.music_band.request.CreateUserRequest;
import dto.music_band.response.AuthUserView;
import dto.music_band.response.UserView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import se.ifmo.pepe.soa1.service.UserService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthUserView> login(@Valid @RequestBody AuthUserRequest request) {
        AuthUserView authUserView = userService.authenticate(request);
        return ResponseEntity.ok()
                              .header(HttpHeaders.AUTHORIZATION, authUserView.getToken())
                              .body(authUserView);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserView> signup(@Valid @RequestBody CreateUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.register(request));
    }
}
