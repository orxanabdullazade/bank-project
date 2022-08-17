package com.unibank.unitech.service.impl;

import com.unibank.unitech.model.User;
import com.unibank.unitech.repository.UserRepository;
import com.unibank.unitech.request.AuthRequest;
import com.unibank.unitech.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public void registerUser(AuthRequest authRequest) {

        Optional<User> registeredUser = userRepository.findByPin(authRequest.getPin());
        if (registeredUser.isPresent()) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            User user=new User();
            user.setPin(authRequest.getPin());
            user.setPassword(authRequest.getPassword());
            userRepository.save(user);
        }

    }

    @Override
    public void loginUser(AuthRequest authRequest) {
        Optional<User> loginUser = userRepository.findByPinAndPassword(authRequest.getPin(), authRequest.getPassword());
        if (!loginUser.isPresent()) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return;
        }
////        userService.getAllUsers().stream().map(u -> new UserResponse(u)).toList();
//        return employeeRepository.findById(id)
//                .map(employee -> convertToDto(employee))
//                .orElseThrow(()->new CustomNotFoundException(ErrorCodeEnum.EMPLOYEE_NOT_FOUND));
      //  return 0;
    }
}
