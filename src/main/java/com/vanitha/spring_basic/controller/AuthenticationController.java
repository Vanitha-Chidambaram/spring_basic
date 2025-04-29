package com.vanitha.spring_basic.controller;

import com.vanitha.spring_basic.dao.LoginUserDao;
import com.vanitha.spring_basic.dao.RegisterDao;
import com.vanitha.spring_basic.model.LoginResponse;
import com.vanitha.spring_basic.model.User;
import com.vanitha.spring_basic.service.AuthenticationService;
import com.vanitha.spring_basic.service.JWTService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JWTService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JWTService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/info")
    public String getInfo(){
        System.out.println("Welcome!!");
        return "This site will help you to signup and login";
    }
    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterDao registerUserDto) {
        System.out.println("Signing up...");
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDao loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}