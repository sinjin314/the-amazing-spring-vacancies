/*
 * Copyright (c) 2021.
 * Abdelhadi Hasnaoui <abdelhadi.hasnaoui@protonmail.com>
 * sinjin.tech
 */

package com.apivacancies.lab.location.controller;

import com.apivacancies.lab.location.domain.Apartment;
import com.apivacancies.lab.location.service.ApartmentService;
import com.apivacancies.lab.location.service.ResidencyService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController("/rest/api/apartment")
public class ApartmentController {
     private ResidencyService residencyService;
     private ApartmentService apartmentService;

     public ApartmentController(ResidencyService residencyService, ApartmentService apartmentService) {
          this.residencyService = residencyService;
          this.apartmentService = apartmentService;
     }

     @GetMapping("/apartment")
     @ResponseStatus(HttpStatus.OK)
     public List<Apartment> getApartments(){
          return apartmentService.getApartments();
     }

     @GetMapping("/apartment/{id}")
     @ResponseStatus(HttpStatus.OK)
     public Apartment getApartment(@PathVariable Long id)
     {
          return apartmentService.getApartment(id);
     }
}
