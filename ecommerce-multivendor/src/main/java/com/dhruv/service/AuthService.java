package com.dhruv.service;

import com.dhruv.response.SignupRequest;

public interface AuthService {

    String sentLoginOtp(String email) throws Exception;

    String createUser(SignupRequest req) throws Exception;


}
