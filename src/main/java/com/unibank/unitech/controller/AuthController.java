package com.unibank.unitech.controller;

import com.unibank.unitech.request.AuthRequest;
import com.unibank.unitech.response.BaseResponse;
import com.unibank.unitech.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    public final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<BaseResponse> registerUser(@RequestBody AuthRequest authRequest){
        return ResponseEntity.ok(authService.registerUser(authRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse>  loginUser(@RequestBody AuthRequest authRequest){
        return ResponseEntity.ok(authService.loginUser(authRequest));
    }





}
