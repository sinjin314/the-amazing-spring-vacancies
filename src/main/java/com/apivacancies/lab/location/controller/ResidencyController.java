/*
 * Copyright (c) 2021.
 * Abdelhadi Hasnaoui <abdelhadi.hasnaoui@protonmail.com>
 * sinjin.tech
 */

package com.apivacancies.lab.location.controller;

import com.apivacancies.lab.location.domain.Residency;
import com.apivacancies.lab.location.service.CountryService;
import com.apivacancies.lab.location.service.ResidencyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController("/rest/api/residency")
public class ResidencyController {

    ResidencyService residencyService;
    CountryService countryService;

    public ResidencyController(ResidencyService residencyService, CountryService countryService) {
        this.residencyService = residencyService;
        this.countryService = countryService;
    }

    @GetMapping("/residency")
    @ResponseStatus(HttpStatus.OK)
    public List<Residency> getResidencies() {
        return residencyService.getResidencies();
    }

    @GetMapping("/residency/generate")
    @ResponseStatus(HttpStatus.OK)
    public void generateResidencies() {
        residencyService.generateResidencies();
    }

    @PostMapping("/residency/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Residency> createResidency(@PathVariable Long id, @RequestBody Residency residency) {
        Residency createdResidency = residencyService.createResidency(id, residency);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdResidency.getId())
                .toUri();

        return ResponseEntity.created(uri).body(createdResidency);
    }

    @PutMapping("/residency/{residency_id}/{country_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Residency> updateResidency(@PathVariable Long country_id, @PathVariable Long residency_id, @RequestBody Residency residency) {
        return ResponseEntity.ok().body(residencyService.updateResidency(country_id, residency_id, residency));
    }

    @DeleteMapping("/residency/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteResidency(@PathVariable Long id) {
        residencyService.deleteResidency(id);
    }

    @GetMapping("/residency/country/{country}")
    @ResponseStatus(HttpStatus.OK)
    public List<Residency> getResidencyByCountry(@PathVariable String country) {
        return residencyService.findResidencyByCountry(country);
    }

    @GetMapping("/residency/country-id")
    @ResponseStatus(HttpStatus.OK)
    public List<Residency> findResidencyByCountryId(Long id) {
        return residencyService.findResidencyByCountryId(id);
    }

}
    