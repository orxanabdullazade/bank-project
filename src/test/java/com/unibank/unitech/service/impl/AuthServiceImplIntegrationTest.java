package com.unibank.unitech.service.impl;

import com.unibank.unitech.exception.CustomErrorException;
import com.unibank.unitech.model.User;
import com.unibank.unitech.repository.UserRepository;
import com.unibank.unitech.request.AuthRequest;
import com.unibank.unitech.response.LoginSuccessResponse;
import com.unibank.unitech.response.RegisterSuccessResponse;
import com.unibank.unitech.util.JwtTokenUtil;
import com.unibank.unitech.util.PasswordHasher;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthServiceImplIntegrationTest {

    private String token="eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE2Nj" +
            "EwODQ5MTEsImV4cCI6MTY2MTA4ODUxMSwic3ViIjoiMSJ9.ami9TOUpVJi5X" +
            "dvXrwFE5in_f3EF8Q9cxh-tVK1-6DiWfDHsCfDTDAZ4mY-TrQ49ilDv5e1uCcoxG-BzLH1LtA";

    private UserRepository userRepository;
    private JwtTokenUtil jwtTokenUtil;
    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() throws Exception {
        authService = new AuthServiceImpl(userRepository,jwtTokenUtil);
        when(jwtTokenUtil.validateToken(token)).thenReturn(true);
    }

    @Test
    @SneakyThrows
    public void testRegisterUser(){


    }








}