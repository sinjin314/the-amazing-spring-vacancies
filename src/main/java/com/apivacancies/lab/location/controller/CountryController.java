/*
 * Copyright (c) 2021.
 * Abdelhadi Hasnaoui <abdelhadi.hasnaoui@protonmail.com>
 * sinjin.tech
 */

package com.apivacancies.lab.location.controller;

import com.apivacancies.lab.location.domain.Country;
import com.apivacancies.lab.location.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public List<Country> getCountrys() {
        return countryService.getCountrys();
    }

    @GetMapping("/country/generate")
    @ResponseStatus(HttpStatus.OK)
    public void generateCountrys() {
        countryService.generateCountrys();
    }

    @GetMapping("/country/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Country> getCountry(@PathVariable Long id) {
        Optional<Country> country = countryService.getCountry(id);
        if (!country.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country doesn't exist in database");
        }
        return country;
    }

    @PostMapping("/country")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Country> createCountry(@RequestBody Country country) {
        Country createdCountry = countryService.createNewCountry(country);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdCountry.getId())
                .toUri();

        return ResponseEntity.created(uri).body(createdCountry);
    }

    @PutMapping("/country/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Country> updateCountry(@RequestBody Country country) {
        return ResponseEntity.ok().body(countryService.updateCountry(country.getId(), country));
    }

    @DeleteMapping("/country/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCountry(@PathVariable Long id) {
        countryService.deleteCountry(id);
    }
}
