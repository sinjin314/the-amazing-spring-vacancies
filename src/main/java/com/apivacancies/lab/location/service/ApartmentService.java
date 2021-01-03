/*
 * Copyright (c) 2021.
 * Abdelhadi Hasnaoui <abdelhadi.hasnaoui@protonmail.com>
 * sinjin.tech
 */

package com.apivacancies.lab.location.service;

import com.apivacancies.lab.location.Error.Apartment.ApartmentNotFoundException;
import com.apivacancies.lab.location.Error.Residency.ResidencyNotFoundException;
import com.apivacancies.lab.location.domain.Apartment;
import com.apivacancies.lab.location.domain.Residency;
import com.apivacancies.lab.location.repository.ApartmentRepository;
import com.apivacancies.lab.location.repository.ResidencyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApartmentService {
    private final ApartmentRepository apartmentRepository;
    private final ResidencyRepository residencyRepository;
    private final ResidencyService residencyService;

    public ApartmentService(ApartmentRepository apartmentRepository, ResidencyRepository residencyRepository, ResidencyService residencyService) {
        this.apartmentRepository = apartmentRepository;
        this.residencyRepository = residencyRepository;
        this.residencyService = residencyService;
    }

    public List<Apartment> getApartments() {
        return apartmentRepository.findAll();
    }

    public Apartment getApartment(Long id) {
        Optional<Apartment> apartment = apartmentRepository.findById(id);

        if (!apartment.isPresent()) {
            throw new ApartmentNotFoundException(id);
        } else {
            return apartment.get();
        }
    }

    public Apartment updateApartment(Long id, Apartment apartment) {
        Optional<Apartment> apartmentOptional = apartmentRepository.findById(id);

        if (!apartmentOptional.isPresent()) {
            throw new ApartmentNotFoundException(id);
        } else {
            apartmentRepository.findApartmentById(id);

            return apartmentRepository.save(apartment);
        }
    }

    public void deleteApartment(Long id) {
        Optional<Apartment> apartmentOptional = apartmentRepository.findById(id);

        if (!apartmentOptional.isPresent()) {
            throw new ApartmentNotFoundException(id);
        } else {
            apartmentRepository.deleteById(id);
        }
    }

    public Apartment createApartment(Long residency_id, Apartment apartment) {
        Optional<Residency> residencyOptional = residencyRepository.findById(residency_id);

        if (!residencyOptional.isPresent()) {
            throw new ResidencyNotFoundException(residency_id);
        } else {
            Residency residency = residencyOptional.get();

            residency.addApartment(apartment);

            residencyRepository.save(residency);

            return apartment;
        }
    }

    public List<Apartment> getApartmentByRegion(String region) {
        List<Residency> residencies = residencyService.findResidencyByRegion(region);
        List<Apartment> apartments = new ArrayList<>();

        for (Residency residency : residencies) {
            apartments.addAll(residency.getApartments());
        }

        return apartments;
    }

    public List<Apartment> getApartmentWithPool() {
        List<Residency> residencies = residencyService.findResidencyWithPool();
        List<Apartment> apartments = new ArrayList<>();

        for (Residency residency : residencies) {
            apartments.addAll(residency.getApartments());
        }

        return apartments;
    }

    public List<Apartment> getApartmentAtMountain() {
        List<Residency> residencies = residencyService.findResidencyAtMountain();
        List<Apartment> apartments = new ArrayList<>();

        for (Residency residency : residencies) {
            apartments.addAll(residency.getApartments());
        }

        return apartments;
    }

}
