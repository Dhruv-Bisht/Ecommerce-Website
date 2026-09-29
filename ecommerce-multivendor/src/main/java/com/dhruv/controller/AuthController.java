package com.dhruv.controller;

import com.dhruv.model.User;
import com.dhruv.repository.UserRepository;
import com.dhruv.response.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth") // Map with Request in the AUTH.
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    @PostMapping("/signup") // Post Mapping means we are adding data in the DATABASE.
    public ResponseEntity<User> createUserHandler(@RequestBody SignupRequest req){


        User user = new User(); // creating new user
        user.setEmail(req.getEmail()); // get the email
        user.setFullName(req.getFullName()); // get the full Name.

        User savedUsers = userRepository.save(user);
        // Just save the data
        return ResponseEntity.ok(savedUsers);
    }
}
