package com.unibank.unitech.dto;


import com.unibank.unitech.model.User;
import lombok.Data;

@Data
public class AuthDto {

    Long id;
    String pin;

    public AuthDto(User user) {
        this.id = user.getId();
        this.pin=user.getPin();
    }
}
