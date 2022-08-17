package com.unibank.unitech.controller;

import com.unibank.unitech.request.AuthRequest;
import com.unibank.unitech.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    public final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody AuthRequest authRequest){
        authService.registerUser(authRequest);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public void loginUser(@RequestBody AuthRequest authRequest){
        authService.loginUser(authRequest);
    }





}
