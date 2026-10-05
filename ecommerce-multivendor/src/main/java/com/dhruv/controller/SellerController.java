package com.dhruv.controller;


import com.dhruv.model.VerificationCode;
import com.dhruv.repository.VerificationCodeRepository;
import com.dhruv.request.LoginRequest;
import com.dhruv.response.ApiResponse;
import com.dhruv.response.AuthResponse;
import com.dhruv.service.AuthService;
import com.dhruv.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers") // all the sellers with with this mapping
public class SellerController {
    private final SellerService sellerService;
    private final VerificationCodeRepository verificationCodeRepository;
    private final AuthService authService;



    @PostMapping("/send/login-signup-otp")
    public ResponseEntity<ApiResponse> sendOtpHandler(
            @RequestBody VerificationCode req) throws Exception {

        authService.sentLoginOtp(req.getEmail());
        ApiResponse res = new ApiResponse();
        res.setMessage("OTP sent successfully");

        return ResponseEntity.ok(res);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginSeller(
        @RequestBody LoginRequest req
    ) throws Exception {
        String otp = req.getOtp();
        String email = req.getEmail();



        req.setEmail("seller_"+email);
        AuthResponse authResponse = authService.signing(req);
        return ResponseEntity.ok(authResponse);
    }

}
