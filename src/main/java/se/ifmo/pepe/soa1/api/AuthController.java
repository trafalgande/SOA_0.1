package se.ifmo.pepe.soa1.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import se.ifmo.pepe.soa1.dto.request.AuthUserRequest;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    @PostMapping("/login")
    public void login(@Valid @RequestBody AuthUserRequest request) {

    }
}
