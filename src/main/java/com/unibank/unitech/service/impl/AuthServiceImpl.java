package com.unibank.unitech.service.impl;

import com.unibank.unitech.exception.CustomErrorException;
import com.unibank.unitech.exception.ErrorCodeEnum;
import com.unibank.unitech.model.User;
import com.unibank.unitech.repository.UserRepository;
import com.unibank.unitech.request.AuthRequest;
import com.unibank.unitech.response.BaseResponse;
import com.unibank.unitech.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;



@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public BaseResponse registerUser(AuthRequest authRequest) {

        Optional<User> registeredUser = userRepository.findByPin(authRequest.getPin());
        if (registeredUser.isPresent()) {
            throw new CustomErrorException(ErrorCodeEnum.ALREADY_REGISTERED);
        } else {
            User user=new User();
            user.setPin(authRequest.getPin());
            user.setPassword(authRequest.getPassword());
            userRepository.save(user);
            return BaseResponse
                    .builder()
                    .success("true")
                    .build();
        }

    }

    @Override
    public BaseResponse loginUser(AuthRequest authRequest) {
        Optional<User> loginUser = userRepository.findByPinAndPassword(authRequest.getPin(), authRequest.getPassword());
        if (loginUser.isPresent()) {
            return BaseResponse
                    .builder()
                    .success("true")
                    .build();
        } else {
            throw new CustomErrorException(ErrorCodeEnum.WRONG_CREDENTIALS);
        }
    }
}
