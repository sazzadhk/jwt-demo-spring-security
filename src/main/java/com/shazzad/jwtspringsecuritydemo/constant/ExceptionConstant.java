package com.shazzad.jwtspringsecuritydemo.constant;

import lombok.Getter;

@Getter
public enum ExceptionConstant {

    USER_NOT_FOUND("User not found with username"),
    ROLE_NOT_FOUND("Role not found with Id"),
    REFRESH_TOKEN_NOT_FOUND("Refresh Token not found with Id");

    private final String message;
    ExceptionConstant(String message){
        this.message = message;
    }
}
