package com.dhruv.controller;

import com.dhruv.model.User;
import com.dhruv.response.SignupRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping("/signup")
    public ResponseEntity<User> createUserHandler(@RequestBody SignupRequest req){


        User user = new User();
        user.setEmail(req.getEmail());
        user.setFullName(req.getFullName());

        return ResponseEntity.ok(user);
    }
}
