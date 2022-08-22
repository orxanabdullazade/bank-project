package com.unibank.unitech.service.impl;

import com.unibank.unitech.exception.CustomErrorException;
import com.unibank.unitech.exception.ErrorCodeEnum;
import com.unibank.unitech.model.User;
import com.unibank.unitech.repository.CurrencyRepository;
import com.unibank.unitech.repository.UserRepository;
import com.unibank.unitech.request.AuthRequest;
import com.unibank.unitech.response.LoginSuccessResponse;
import com.unibank.unitech.response.RegisterSuccessResponse;
import com.unibank.unitech.util.JwtTokenUtil;
import com.unibank.unitech.util.PasswordHasher;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceImplTest {

    private String token="eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE2Nj" +
            "EwODQ5MTEsImV4cCI6MTY2MTA4ODUxMSwic3ViIjoiMSJ9.ami9TOUpVJi5X" +
            "dvXrwFE5in_f3EF8Q9cxh-tVK1-6DiWfDHsCfDTDAZ4mY-TrQ49ilDv5e1uCcoxG-BzLH1LtA";

    private UserRepository userRepository;
    private JwtTokenUtil jwtTokenUtil;
    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() throws Exception {
        userRepository = mock(UserRepository.class);
        jwtTokenUtil = mock(JwtTokenUtil.class);
        authService = new AuthServiceImpl(userRepository,jwtTokenUtil);
        when(jwtTokenUtil.validateToken(token)).thenReturn(true);
    }

    @Test
    @SneakyThrows
    public void testRegisterUser(){
        AuthRequest authRequest=AuthRequest.builder()
                .pin("test1")
                .password("1234")
                .build();
       User user= User.builder()
                .id(3)
                .pin("test2")
                .password(PasswordHasher.hashPassword("123"))
                .build();

        when(userRepository.findByPin(authRequest.getPin())).thenReturn(Optional.empty());
        when(userRepository.save(user)).thenReturn(user);

        RegisterSuccessResponse registerSuccessResponse=authService.registerUser(authRequest);

        assertNotNull(registerSuccessResponse);
        assertEquals("true",registerSuccessResponse.getSuccess());

    }

    @Test
    @SneakyThrows
    public void testRegisterUserWithError(){
        Exception ex= assertThrows(CustomErrorException.class,()-> {
            AuthRequest authRequest=AuthRequest.builder()
                    .pin("test1")
                    .password("1234")
                    .build();
            User user= User.builder()
                    .id(3)
                    .pin("test2")
                    .password(PasswordHasher.hashPassword("123"))
                    .build();

            when(userRepository.findByPin(authRequest.getPin())).thenReturn(Optional.of(user));
            when(userRepository.save(user)).thenReturn(user);

            RegisterSuccessResponse registerSuccessResponse = authService.registerUser(authRequest);

            assertNotNull(registerSuccessResponse);
            assertEquals("true", registerSuccessResponse.getSuccess());
        });
        assertEquals("pin already registered",ex.getMessage());

    }

    @Test
    @SneakyThrows
    public void testLoginUserSuccess(){
        AuthRequest authRequest=AuthRequest.builder()
                .pin("orxan")
                .password("123")
                .build();
        Optional<User> user= Optional.ofNullable(User.builder()
                .id(1)
                .pin("orxan")
                .password(PasswordHasher.hashPassword("123"))
                .build());
        when(userRepository.findByPin(authRequest.getPin())).thenReturn(user);
        when(jwtTokenUtil.generateToken(String.valueOf(user.get().getId()))).thenReturn(token);

        LoginSuccessResponse loginSuccessResponse=authService.loginUser(authRequest);

        assertNotNull(loginSuccessResponse);
        assertEquals("true",loginSuccessResponse.getSuccess());
        assertEquals(token,loginSuccessResponse.getToken());

    }

    @Test
    @SneakyThrows
    public void testLoginUserFailureWithWrongPin(){
        Exception ex= assertThrows(CustomErrorException.class,()->{
            AuthRequest authRequest=AuthRequest.builder()
                    .pin("orxan")
                    .password("123")
                    .build();
            Optional<User> user= Optional.ofNullable(User.builder()
                    .id(1)
                    .pin("orxan")
                    .password(PasswordHasher.hashPassword("123"))
                    .build());
            when(userRepository.findByPin(authRequest.getPin())).thenReturn(Optional.empty());
            when(jwtTokenUtil.generateToken(String.valueOf(user.get().getId()))).thenReturn(token);

            LoginSuccessResponse loginSuccessResponse=authService.loginUser(authRequest);

            assertNotNull(loginSuccessResponse);
            assertEquals("true",loginSuccessResponse.getSuccess());
            assertEquals(token,loginSuccessResponse.getToken());
        });
        assertEquals("pin or password is wrong",ex.getMessage());

    }

    @Test
    @SneakyThrows
    public void testLoginUserFailureWithWrongPassword(){
        Exception ex= assertThrows(CustomErrorException.class,()->{
            AuthRequest authRequest=AuthRequest.builder()
                    .pin("orxan")
                    .password("123")
                    .build();
            Optional<User> user= Optional.ofNullable(User.builder()
                    .id(1)
                    .pin("orxan")
                    .password(PasswordHasher.hashPassword("12345"))
                    .build());
            when(userRepository.findByPin(authRequest.getPin())).thenReturn(user);
            when(jwtTokenUtil.generateToken(String.valueOf(user.get().getId()))).thenReturn(token);

            LoginSuccessResponse loginSuccessResponse=authService.loginUser(authRequest);

            assertNotNull(loginSuccessResponse);
            assertEquals("true",loginSuccessResponse.getSuccess());
            assertEquals(token,loginSuccessResponse.getToken());
        });
        assertEquals("pin or password is wrong",ex.getMessage());

    }


}