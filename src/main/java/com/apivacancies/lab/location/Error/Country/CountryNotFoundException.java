/*
 * Copyright (c) 2021.
 * Abdelhadi Hasnaoui <abdelhadi.hasnaoui@protonmail.com>
 * sinjin.tech
 */

package com.apivacancies.lab.location.Error.Country;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(Object id) {
        super("Could not find country with id " + id + ".");
    }
}
