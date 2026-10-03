package com.dhruv.controller;


import com.dhruv.response.AuthResponse;
import com.dhruv.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<AuthResponse> createUserHandler() throws Exception{


        return ResponseEntity.ok(res);
    }


}
