package com.unibank.unitech.service.impl;

import com.unibank.unitech.exception.CustomErrorException;
import com.unibank.unitech.exception.ErrorCodeEnum;
import com.unibank.unitech.model.User;
import com.unibank.unitech.repository.UserRepository;
import com.unibank.unitech.request.AuthRequest;
import com.unibank.unitech.response.RegisterSuccessResponse;
import com.unibank.unitech.response.LoginSuccessResponse;
import com.unibank.unitech.service.AuthService;
import com.unibank.unitech.util.JwtTokenUtil;
import com.unibank.unitech.util.PasswordHasher;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Optional;



@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;

    @SneakyThrows
    @Override
    public RegisterSuccessResponse registerUser(AuthRequest authRequest) {

        Optional<User> registeredUser = userRepository.findByPin(authRequest.getPin());

        if (registeredUser.isPresent()) {
            throw new CustomErrorException(ErrorCodeEnum.ALREADY_REGISTERED);
        } else {
            String password=PasswordHasher.hashPassword(authRequest.getPassword());

            User user=new User();
            user.setPin(authRequest.getPin());
            user.setPassword(password);
            userRepository.save(user);
            return RegisterSuccessResponse
                    .builder()
                    .success("true")
                    .build();
        }

    }

    @SneakyThrows
    @Override
    public LoginSuccessResponse loginUser(AuthRequest authRequest) {
        User loginUser = userRepository.findByPin(authRequest.getPin())
                .orElseThrow(() -> new CustomErrorException(ErrorCodeEnum.WRONG_CREDENTIALS));

        if(PasswordHasher.verifyPassword(authRequest.getPassword(),loginUser.getPassword())) {
            String token = jwtTokenUtil.generateToken(String.valueOf(loginUser.getId()));

            return LoginSuccessResponse
                    .builder()
                    .success("true")
                    .token(token)
                    .build();
        }else{
            throw new CustomErrorException(ErrorCodeEnum.WRONG_CREDENTIALS);
        }
    }


}
