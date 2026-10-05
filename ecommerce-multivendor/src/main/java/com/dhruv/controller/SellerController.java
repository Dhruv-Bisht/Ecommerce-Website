package com.dhruv.controller;


import com.dhruv.model.VerificationCode;
import com.dhruv.repository.VerificationCodeRepository;
import com.dhruv.response.ApiResponse;
import com.dhruv.response.AuthResponse;
import com.dhruv.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers") // all the sellers with with this mapping
public class SellerController {
    private final SellerService sellerService;
    private final VerificationCodeRepository verificationCodeRepository;
    public ResponseEntity<AuthResponse> loginSeller(
        @RequestBody VerificationCode req
    ){
        String otp = req.getOtp();
        String email = req.getEmail();


        return null;
    }

}
