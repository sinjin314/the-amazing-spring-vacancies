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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
        return countryService.getCountrys();
    }

    @GetMapping("/country/generate")
    @ResponseStatus(HttpStatus.OK)
    public void generateCountrys() {
        countryService.generateCountrys();
    }

    @GetMapping("/country/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Country> getCountry(@PathVariable Long id)
    {
        Optional<Country> country = countryService.getCountry(id);
        if(!country.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country doesn't exist in database");
        }
        return country;
    }
}
