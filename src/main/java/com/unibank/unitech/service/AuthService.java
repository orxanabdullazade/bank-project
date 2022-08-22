package com.unibank.unitech.service;

import com.unibank.unitech.request.AuthRequest;
import com.unibank.unitech.response.RegisterSuccessResponse;
import com.unibank.unitech.response.LoginSuccessResponse;
import org.springframework.stereotype.Service;


@Service
public interface AuthService {

    RegisterSuccessResponse registerUser(AuthRequest authRequest);

    LoginSuccessResponse loginUser(AuthRequest authRequest);
}
