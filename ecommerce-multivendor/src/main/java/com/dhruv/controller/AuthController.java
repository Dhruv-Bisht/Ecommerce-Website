package com.dhruv.controller;

import com.dhruv.domain.USER_ROLE;
import com.dhruv.model.User;
import com.dhruv.repository.UserRepository;
import com.dhruv.response.AuthResponse;
import com.dhruv.response.SignupRequest;
import com.dhruv.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth") // Map with Request in the AUTH.
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final AuthService authService;
    @PostMapping("/signup") // Post Mapping means we are adding data in the DATABASE.
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignupRequest req){

        String jwt = authService.createUser(req);
        AuthResponse res = new AuthResponse();
        res.setJwt(jwt);
        res.setMessage("Register Success.");
        res.setRole(USER_ROLE.ROLE_CUSTOMER);

        return ResponseEntity.ok(res);
    }
}
