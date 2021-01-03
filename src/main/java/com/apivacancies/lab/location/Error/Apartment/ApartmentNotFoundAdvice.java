/*
 * Copyright (c) 2021.
 * Abdelhadi Hasnaoui <abdelhadi.hasnaoui@protonmail.com>
 * sinjin.tech
 */

package com.apivacancies.lab.location.Error.Apartment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ApartmentNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(ApartmentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String apartmentNotFoundHandler(ApartmentNotFoundException ex) {
        return ex.getMessage();
    }
}
