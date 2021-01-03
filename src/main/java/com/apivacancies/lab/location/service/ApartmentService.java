/*
 * Copyright (c) 2021.
 * Abdelhadi Hasnaoui <abdelhadi.hasnaoui@protonmail.com>
 * sinjin.tech
 */

package com.apivacancies.lab.location.service;

import com.apivacancies.lab.location.Error.Apartment.ApartmentNotFoundException;
import com.apivacancies.lab.location.domain.Apartment;
import com.apivacancies.lab.location.repository.ApartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApartmentService {
    private ApartmentRepository apartmentRepository;

    public ApartmentService(ApartmentRepository apartmentRepository)
    {
        this.apartmentRepository = apartmentRepository;
    }

    public List<Apartment> getApartments() {
        return apartmentRepository.findAll();
    }

    public Apartment getApartment(Long id) {
        Optional<Apartment> apartment = apartmentRepository.findById(id);

        if( !apartment.isPresent() )
        {
            throw new ApartmentNotFoundException(id);
        } else {
            return apartment.get();
        }
    }
}
