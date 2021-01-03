/*
 * Copyright (c) 2021.
 * Abdelhadi Hasnaoui <abdelhadi.hasnaoui@protonmail.com>
 * sinjin.tech
 */

package com.apivacancies.lab.location.Error.Residency;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ResidencyNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ResidencyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String residencyNotFoundHandler(ResidencyNotFoundException ex) {
        return ex.getMessage();
    }
}
