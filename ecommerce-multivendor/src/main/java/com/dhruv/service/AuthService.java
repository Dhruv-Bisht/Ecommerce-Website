package com.dhruv.service;

import com.dhruv.model.User;
import com.dhruv.response.SignupRequest;

public interface AuthService {

    void sentLoginOtp(String email) throws Exception;

    String createUser(SignupRequest req) throws Exception;


}
