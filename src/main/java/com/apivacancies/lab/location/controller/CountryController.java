/*
 * Copyright (c) 2021.
 * Abdelhadi Hasnaoui <abdelhadi.hasnaoui@protonmail.com>
 * sinjin.tech
 */

package com.apivacancies.lab.location.controller;

import com.apivacancies.lab.location.domain.Country;
import com.apivacancies.lab.location.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController("/rest/api/country")
public class CountryController {

    CountryService countryService;
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/country")
    @ResponseStatus(HttpStatus.OK)
    public List<Country> getCountrys()
    {
        return countryService.getCountry();
    }

}
