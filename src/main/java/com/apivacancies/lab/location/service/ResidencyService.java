/*
 * Copyright (c) 2021.
 * Abdelhadi Hasnaoui <abdelhadi.hasnaoui@protonmail.com>
 * sinjin.tech
 */

package com.apivacancies.lab.location.service;

import com.apivacancies.lab.location.repository.ResidencyRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ResidencyService {
    @Autowired
    private ResidencyRepository residencyRepository;

    public ResidencyRepository() {

    }
}
