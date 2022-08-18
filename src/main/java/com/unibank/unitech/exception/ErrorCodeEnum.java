package com.unibank.unitech.exception;

public enum ErrorCodeEnum {

    ALREADY_REGISTERED(1001,"pin already registered"),
    WRONG_CREDENTIALS(1002,"pin or password is wrong"),
    BALANCE_LOW(1003,"no enough money in my account balance"),
    TRANSFER_SAME_ACCOUNT(1004,"transfer to same account"),
    TRANSFER_DEACTIVE_ACCOUNT(1005,"transfer to deactive account"),
    TRANSFER_NON_EXISTING_ACCOUNT(1006,"transfer to non existing account"),

    UNKNOWN_ERROR(1000," unkown error");

    private final int code;
    private final String message;

    ErrorCodeEnum(int code,String message) {
        this.code=code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    public int getCode() { return code; }

}
