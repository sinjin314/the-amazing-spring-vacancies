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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

     @PutMapping("/apartment/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public ResponseEntity<Apartment> updateApartment(@PathVariable Long id, @RequestBody Apartment apartment )
     {
          return ResponseEntity.ok().body(apartmentService.updateApartment(id, apartment));
     }

     @DeleteMapping("/apartment/{id}")
     @ResponseStatus(HttpStatus.OK)
     public void deleteApartment(@PathVariable Long id) {
          apartmentService.deleteApartment(id);
     }

     @PostMapping("/apartment/{residency_id}")
     @ResponseStatus(HttpStatus.CREATED)
     public ResponseEntity<Apartment> createApartments(@PathVariable Long residency_id, @RequestBody Apartment apartment) {
          Apartment createdApartment = apartmentService.createApartment(residency_id, apartment);
          URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                  .path("/residency_id")
                  .buildAndExpand(createdApartment.getId())
                  .toUri();

          return ResponseEntity.created(uri).body(createdApartment);
     }
}
