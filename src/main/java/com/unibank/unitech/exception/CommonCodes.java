package com.unibank.unitech.exception;

public enum CommonCodes {
    FORBIDDEN(1),
    HTTP_REQUEST_ERROR(2),
    RESOURCE_NOT_FOUND(3),
    VALIDATION_FAILED(4),
    INTERNAL_ERROR(5);

    private final int number;

    CommonCodes(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
