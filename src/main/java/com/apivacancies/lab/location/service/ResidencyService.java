/*
 * Copyright (c) 2021.
 * Abdelhadi Hasnaoui <abdelhadi.hasnaoui@protonmail.com>
 * sinjin.tech
 */

package com.apivacancies.lab.location.service;

import com.apivacancies.lab.location.Error.Country.CountryNotFoundException;
import com.apivacancies.lab.location.Error.Residency.ResidencyNotFoundException;
import com.apivacancies.lab.location.domain.Apartment;
import com.apivacancies.lab.location.domain.Country;
import com.apivacancies.lab.location.domain.Residency;
import com.apivacancies.lab.location.repository.ApartmentRepository;
import com.apivacancies.lab.location.repository.CountryRepository;
import com.apivacancies.lab.location.repository.ResidencyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResidencyService {

    private final ResidencyRepository residencyRepository;
    private final CountryRepository countryRepository;
    private final ApartmentRepository apartmentRepository;

    public ResidencyService(ResidencyRepository residencyRepository, CountryRepository countryRepository, ApartmentRepository apartmentRepository) {
        this.residencyRepository = residencyRepository;
        this.countryRepository = countryRepository;
        this.apartmentRepository = apartmentRepository;
    }

    public List<Residency> getResidencies() {
        return residencyRepository.findAll();
    }

    public void generateResidencies() {
        Country country = new Country();
        country.setName("France");
        countryRepository.save(country);

        Apartment apartment = new Apartment();

        apartment.setBabyEquipment(true);
        apartment.setBedding(4);
        apartment.setPrice((float) 4000);
        apartment.setSurface(6000);
        apartment.setClim(true);

        apartmentRepository.save(apartment);

        Residency residency = new Residency();
        residency.setAddress("dubai");
        residency.setCountry(country);
        residency.setLocType(Residency.LocationType.OCEAN);
        residency.setNursery(true);
        residency.setPool(false);
        residency.setRegion("Occitanie");

        residency.getApartments().add(apartment);

        residencyRepository.save(residency);

        country = new Country();
        country.setName("Canada");
        countryRepository.save(country);

        apartment = new Apartment();

        apartment.setBabyEquipment(true);
        apartment.setBedding(4);
        apartment.setPrice((float) 4000);
        apartment.setSurface(6000);
        apartment.setClim(true);

        apartmentRepository.save(apartment);

        residency = new Residency();
        residency.setAddress("vancouver");
        residency.setCountry(country);
        residency.setLocType(Residency.LocationType.MOUNTAIN);
        residency.setNursery(true);
        residency.setPool(false);
        residency.setRegion("Champs De Mais");

        residency.getApartments().add(apartment);

        residencyRepository.save(residency);

    }

    public Residency createResidency(Long id, Residency residency) {
        Optional<Country> countryLinked = countryRepository.findById(id);

        if (!countryLinked.isPresent()) {
            throw new CountryNotFoundException(id);
        } else {
            Residency newResidency = new Residency();
            newResidency.setAddress(residency.getAddress());
            newResidency.setCountry(countryLinked.get());
            newResidency.setLocType(residency.getLocType());
            newResidency.setNursery(residency.getNursery());
            newResidency.setPool(residency.getPool());
            newResidency.setRegion(residency.getRegion());
            newResidency.setSpa(residency.getSpa());
            newResidency.setLatitude(residency.getLatitude());
            newResidency.setLongitude(residency.getLongitude());

            return residencyRepository.save(newResidency);
        }
    }

    public Residency updateResidency(Long country_id, Long residency_id, Residency residency) {
        Optional<Country> countryLinked = countryRepository.findById(country_id);
        Optional<Residency> updatedResidency = residencyRepository.findById(residency_id);

        if (!countryLinked.isPresent()) {
            throw new CountryNotFoundException(country_id);
        } else {
            if (!updatedResidency.isPresent()) {
                throw new ResidencyNotFoundException(residency_id);
            } else {
                residencyRepository.findById(residency_id);

                Residency residencyToBeUpdated = updatedResidency.get();

                residencyToBeUpdated.setAddress(residency.getAddress());
                residencyToBeUpdated.setRegion(residency.getRegion());

                residencyToBeUpdated.setLongitude(residency.getLongitude());
                residencyToBeUpdated.setLatitude(residency.getLatitude());

                residencyToBeUpdated.setCountry(countryLinked.get());
                residencyToBeUpdated.setLocType(residency.getLocType());

                residencyToBeUpdated.setNursery(residency.getNursery());
                residencyToBeUpdated.setPool(residency.getPool());

                residencyToBeUpdated.setSpa(residency.getSpa());


                return residencyRepository.save(residencyToBeUpdated);
            }
        }
    }

    public void deleteResidency(Long id) {
        Optional<Residency> residencyOptional = residencyRepository.findById(id);

        if (!residencyOptional.isPresent()) {
            throw new ResidencyNotFoundException(id);
        } else {
            residencyRepository.deleteById(id);
        }
    }

    public List<Residency> findResidencyByCountry(String country_name) {
        Country country = countryRepository.findCountryByName(country_name).get(0);
        return residencyRepository.findResidencyByCountryId(country.getId());
    }

    public List<Residency> findResidencyByCountryId(Long id) {
        return residencyRepository.findResidencyByCountryId(id);
    }

    public List<Residency> findResidencyByRegion(String region) {
        return residencyRepository.findResidencyByRegion(region);
    }

    public List<Residency> findResidencyWithPool() {
        return residencyRepository.findResidencyWithPool();
    }

    public List<Residency> findResidencyAtMountain() {
        return residencyRepository.findResidencyAtMountain();
    }
}
