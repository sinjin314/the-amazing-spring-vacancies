/*
 * Copyright (c) 2021.
 * Abdelhadi Hasnaoui <abdelhadi.hasnaoui@protonmail.com>
 * sinjin.tech
 */

package com.apivacancies.lab.location.Error.Residency;

public class ResidencyNotFoundException extends RuntimeException {
    public ResidencyNotFoundException(Long id) {
        super("Could not find Residency with id " + id + ".");
    }
}
