package com.shazzad.jwtspringsecuritydemo.exception;

import com.shazzad.jwtspringsecuritydemo.constant.ExceptionConstant;

public class ResourceNotFoundException extends ApplicationException{
    public ResourceNotFoundException(ExceptionConstant exceptionConstant) {
        super(exceptionConstant.getMessage(), exceptionConstant.name());
    }
}
