/*
 * Copyright (c) 2021.
 * Abdelhadi Hasnaoui <abdelhadi.hasnaoui@protonmail.com>
 * sinjin.tech
 */

package com.apivacancies.lab.location.controller;

import com.apivacancies.lab.location.domain.Apartment;
import com.apivacancies.lab.location.service.ApartmentService;
import com.apivacancies.lab.location.service.ResidencyService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@Controller
@RestController("/rest/api/apartment")
public class ApartmentController {
    private final ResidencyService residencyService;
    private final ApartmentService apartmentService;

    public ApartmentController(ResidencyService residencyService, ApartmentService apartmentService) {
        this.residencyService = residencyService;
        this.apartmentService = apartmentService;
    }

    @GetMapping("/apartments")
    @ResponseStatus(HttpStatus.OK)
    public List<Apartment> getApartments() {
        return apartmentService.getApartments();
    }

    @GetMapping("/apartment")
    @ResponseStatus(HttpStatus.OK)
    public Apartment getApartment(Long appartId) {
        return apartmentService.getApartment(appartId);
    }

    @PutMapping("/apartment")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Apartment> updateApartment(@RequestBody Apartment apartment) {
        return ResponseEntity.ok().body(apartmentService.updateApartment(apartment.getId(), apartment));
    }

    @DeleteMapping("/apartment")
    @ResponseStatus(HttpStatus.OK)
    public void deleteApartment(Long appartId) {
        apartmentService.deleteApartment(appartId);
    }

    @PostMapping("/apartment")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Apartment> createApartments(Long residency_id, @RequestBody Apartment apartment) {
        Apartment createdApartment = apartmentService.createApartment(residency_id, apartment);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/residency_id")
                .buildAndExpand(createdApartment.getId())
                .toUri();

        return ResponseEntity.created(uri).body(createdApartment);
    }

    @GetMapping("/appartment/region")
    @ResponseStatus(HttpStatus.OK)
    public List<Apartment> getApartmentByRegion(String region) {
        return apartmentService.getApartmentByRegion(region);
    }

    @GetMapping("/apartment/pool")
    @ResponseStatus(HttpStatus.OK)
    public List<Apartment> getApartmentWithPool() {
        return apartmentService.getApartmentWithPool();
    }

    @GetMapping("/apartment/mountainView")
    @ResponseStatus(HttpStatus.OK)
    public List<Apartment> findApartmentWithMountain() {
        return apartmentService.getApartmentAtMountain();
    }

    @GetMapping("/apartment/available")
    @ResponseStatus(HttpStatus.OK)
    public List<Apartment> findAvailableApartment(@RequestParam(value = "checkIn")
                                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                        LocalDate checkIn,
                                                  @RequestParam(value = "checkOut")
                                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                          LocalDate checkOut) {
        return apartmentService.findAvailableApartBetweenDate(checkIn, checkOut);
    }
}
