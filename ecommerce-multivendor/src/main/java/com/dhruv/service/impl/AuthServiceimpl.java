package com.dhruv.service.impl;

import com.dhruv.config.JwtProvider;
import com.dhruv.domain.USER_ROLE;
import com.dhruv.model.Cart;
import com.dhruv.model.Seller;
import com.dhruv.model.User;
import com.dhruv.model.VerificationCode;
import com.dhruv.repository.CartRepository;
import com.dhruv.repository.SellerRepository;
import com.dhruv.repository.UserRepository;
import com.dhruv.repository.VerificationCodeRepository;
import com.dhruv.request.LoginRequest;
import com.dhruv.response.AuthResponse;
import com.dhruv.response.SignupRequest;
import com.dhruv.service.AuthService;
import com.dhruv.service.EmailService;
import com.dhruv.utils.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
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
    private final CustomUserServiceImpl customUserService;
    private SellerRepository sellerRepository;


    @Override
    public String sentLoginOtp(String email, USER_ROLE role) throws Exception {
        String SIGNING_PREFIX = "signin_";
        String SELLER_PREFIX = "seller_";

//        VerificationCode existingCode =
//                verificationCodeRepository.findByEmail(email);
//
//        if (existingCode != null) {
//            verificationCodeRepository.delete(existingCode);
//        }
//
//        String otp = OtpUtil.generateOtp();
//
//        VerificationCode verificationCode = new VerificationCode();
//        verificationCode.setOtp(otp);
//        verificationCode.setEmail(email);
//
//        verificationCodeRepository.save(verificationCode);
//
//        String subject = "Dhruv Ecom Login/Signup OTP";
//        String text = "Your login/signup OTP is - " + otp;
//
//        emailService.sendVerificationOtpEmail(
//                email,
//                otp,
//                subject,
//                text
//        );

        if(email.startsWith(SIGNING_PREFIX)){
            email = email.substring(SIGNING_PREFIX.length());

            if(role.equals(USER_ROLE.ROLE_SELLER)){
                Seller seller = sellerRepository.findByEmail(email);
                if(seller == null){
                    throw new Exception("user not exist with provided email.");
                }
            }
            else{
                User user = userRepository.findByEmail(email);
                if(user == null){
                    throw new Exception("Seller not found");
                }
            }

        }

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

    @Override
    public AuthResponse signing(LoginRequest req) {
        String username = req.getEmail();
        String otp = req.getOtp();

        Authentication authentication = authenticate(username, otp);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Login Success");

        Collection<?extends GrantedAuthority> authorities = authentication.getAuthorities();
        String roleName = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();

        authResponse.setRole(USER_ROLE.valueOf(roleName));


        return authResponse;
    }

    private Authentication authenticate(String username, String otp) {
//        we will verify otp

        UserDetails userDetails = customUserService.loadUserByUsername(username);
        if(userDetails == null){
            throw new BadCredentialsException("Invalid username or password");
        }

        VerificationCode verificationCode = verificationCodeRepository.findByEmail(username);
        if (verificationCode == null || !verificationCode.getOtp().equals(otp)){
            throw new BadCredentialsException("Wrong OTP");
        }



        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
