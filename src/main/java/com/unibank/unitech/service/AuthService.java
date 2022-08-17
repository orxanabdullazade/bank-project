package com.unibank.unitech.service;

import com.unibank.unitech.request.AuthRequest;
import org.springframework.stereotype.Service;


@Service
public interface AuthService {

    void registerUser(AuthRequest authRequest);

    void loginUser(AuthRequest authRequest);
}
