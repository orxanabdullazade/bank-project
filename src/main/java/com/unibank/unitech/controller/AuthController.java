package com.unibank.unitech.controller;

import com.unibank.unitech.request.AuthRequest;
import com.unibank.unitech.response.RegisterSuccessResponse;
import com.unibank.unitech.response.LoginSuccessResponse;
import com.unibank.unitech.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Auth Service")
public class AuthController {
//sha1:128000:72:e5EkD47NIS1f8aLq9RjmMZVs9CgumtjGSQTViP0cDM59m1+ffqbiPDV/hEt9EXPieSPlBsj34t6eWmkR5q9uIEyqVOO43w+V:DRM0sktpxUyyj7WeQFCzNVvs0jBtHrXsxV/xituj4ulhQzVdlQsZE9lWzJ9CIW80C26sPX/OcXPNPRrn0+0k8O89UDwrfCum
    public final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterSuccessResponse> registerUser(@RequestBody AuthRequest authRequest){
        return ResponseEntity.ok(authService.registerUser(authRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginSuccessResponse>  loginUser(@RequestBody AuthRequest authRequest){
        return ResponseEntity.ok(authService.loginUser(authRequest));
    }





}
