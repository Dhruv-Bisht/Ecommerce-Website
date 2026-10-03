package com.dhruv.service;

import com.dhruv.request.LoginRequest;
import com.dhruv.response.AuthResponse;
import com.dhruv.response.SignupRequest;

public interface AuthService {

    String sentLoginOtp(String email) throws Exception;

    String createUser(SignupRequest req) throws Exception;

    AuthResponse signing(LoginRequest req);

}
