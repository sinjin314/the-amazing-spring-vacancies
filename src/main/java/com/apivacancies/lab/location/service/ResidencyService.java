/*
 * Copyright (c) 2021.
 * Abdelhadi Hasnaoui <abdelhadi.hasnaoui@protonmail.com>
 * sinjin.tech
 */

package com.apivacancies.lab.location.service;

import com.apivacancies.lab.location.Error.Country.CountryNotFoundException;
import com.apivacancies.lab.location.Error.Residency.ResidencyNotFoundException;
import com.apivacancies.lab.location.domain.Country;
import com.apivacancies.lab.location.domain.Residency;
import com.apivacancies.lab.location.repository.CountryRepository;
import com.apivacancies.lab.location.repository.ResidencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResidencyService {
    @Autowired
    private ResidencyRepository residencyRepository;
    private CountryRepository countryRepository;

    public ResidencyService(ResidencyRepository residencyRepository, CountryRepository countryRepository) {
        this.residencyRepository = residencyRepository;
        this.countryRepository = countryRepository;
    }

    public List<Residency> getResidencies() {
        return residencyRepository.findAll();
    }

    public void generateResidencies(){
        Country country = new Country();
        country.setName("France");
        countryRepository.save(country);

        Residency residency = new Residency();
        residency.setAddress("dubai");
        residency.setCountry(country);
        residency.setLocType(Residency.LocationType.OCEAN);
        residency.setNursery(true);
        residency.setPool(false);
        residency.setRegion("Occitanie");
        residencyRepository.save(residency);

        country = new Country();
        country.setName("Canada");
        countryRepository.save(country);

        residency = new Residency();
        residency.setAddress("vancouver");
        residency.setCountry(country);
        residency.setLocType(Residency.LocationType.MOUNTAIN);
        residency.setNursery(true);
        residency.setPool(false);
        residency.setRegion("Champs De Mais");
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

                Residency residencyToBeUpdated = updatedResidency.get() ;
                residencyToBeUpdated.setAddress(residency.getAddress());
                residencyToBeUpdated.setCountry(countryLinked.get());
                residencyToBeUpdated.setLocType(residency.getLocType());
                residencyToBeUpdated.setNursery(residency.getNursery());
                residencyToBeUpdated.setPool(residency.getPool());
                residencyToBeUpdated.setSpa(residency.getSpa());
                residencyToBeUpdated.setRegion(residency.getRegion());

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
}
