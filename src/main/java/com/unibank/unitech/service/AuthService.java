package com.unibank.unitech.service;

import com.unibank.unitech.request.AuthRequest;
import com.unibank.unitech.response.BaseResponse;
import org.springframework.stereotype.Service;


@Service
public interface AuthService {

    BaseResponse registerUser(AuthRequest authRequest);

    BaseResponse loginUser(AuthRequest authRequest);
}
