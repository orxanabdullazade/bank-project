package com.unibank.unitech.request;

import lombok.Data;

@Data
public class AuthRequest {
    String pin;
    String password;
}
