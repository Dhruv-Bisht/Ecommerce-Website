package com.dhruv.service;

import com.dhruv.model.User;
import com.dhruv.response.SignupRequest;

public interface AuthService {
    String createUser(SignupRequest req);


}
