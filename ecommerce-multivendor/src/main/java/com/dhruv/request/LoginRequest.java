package com.dhruv.request;

public class LoginRequest {
    private String email;

    private String otp;

    public String getOtp() {
        return otp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
