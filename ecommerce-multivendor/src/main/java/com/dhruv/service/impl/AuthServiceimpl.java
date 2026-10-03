package com.dhruv.service.impl;

import com.dhruv.config.JwtProvider;
import com.dhruv.domain.USER_ROLE;
import com.dhruv.model.Cart;
import com.dhruv.model.User;
import com.dhruv.model.VerificationCode;
import com.dhruv.repository.CartRepository;
import com.dhruv.repository.UserRepository;
import com.dhruv.repository.VerificationCodeRepository;
import com.dhruv.response.SignupRequest;
import com.dhruv.service.AuthService;
import com.dhruv.service.EmailService;
import com.dhruv.utils.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceimpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;
    private final JwtProvider jwtProvider;
    private final VerificationCodeRepository verificationCodeRepository;
    private final EmailService emailService;
    @Override
    public String sentLoginOtp(String email) throws Exception {

        VerificationCode existingCode =
                verificationCodeRepository.findByEmail(email);

        if (existingCode != null) {
            verificationCodeRepository.delete(existingCode);
        }

        String otp = OtpUtil.generateOtp();

        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setOtp(otp);
        verificationCode.setEmail(email);

        verificationCodeRepository.save(verificationCode);

        String subject = "Dhruv Ecom Login/Signup OTP";
        String text = "Your login/signup OTP is - " + otp;

        emailService.sendVerificationOtpEmail(
                email,
                otp,
                subject,
                text
        );

        return "OTP sent successfully";
    }

    @Override
    public String createUser(SignupRequest req) throws Exception {


        VerificationCode verificationCode = verificationCodeRepository.findByEmail(req.getEmail());
        // if verification code is not present in the db.
        if(verificationCode == null || !verificationCode.getOtp().equals(req.getOtp())){
            throw new Exception("Wrong otp...");
        }



        User user = userRepository.findByEmail(req.getEmail());
        if(user == null){
            User createdUser = new User();
            createdUser.setEmail(req.getEmail());
            createdUser.setFullName(req.getFullName());
            createdUser.setRole(USER_ROLE.ROLE_CUSTOMER);
            createdUser.setMobile("6397666874");
            createdUser.setPassword(passwordEncoder.encode(req.getOtp()));

            user = userRepository.save(createdUser);
            Cart cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(USER_ROLE.ROLE_CUSTOMER.toString()));

        Authentication authentication = new UsernamePasswordAuthenticationToken(req.getEmail(),null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtProvider.generateToken(authentication);
    }
}
