/*
 * Copyright (c) 2021.
 * Abdelhadi Hasnaoui <abdelhadi.hasnaoui@protonmail.com>
 * sinjin.tech
 */

package com.apivacancies.lab.location.service;

import com.apivacancies.lab.location.Error.Country.CountryNotFoundException;
import com.apivacancies.lab.location.domain.Country;
import com.apivacancies.lab.location.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    @Autowired
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getCountrys() {
        return countryRepository.findAll();
    }

    public Optional<Country> getCountry(long id) {
        return countryRepository.findById(id);
    }

    public void generateCountrys() {
        Country country = new Country();
        country.setName("France");
        countryRepository.save(country);

        country = new Country();
        country.setName("Algeria");
        countryRepository.save(country);

        country = new Country();
        country.setName("Canada");
        countryRepository.save(country);

        country = new Country();
        country.setName("Brasil");
        countryRepository.save(country);

        country = new Country();
        country.setName("Japan");
        countryRepository.save(country);

        country = new Country();
        country.setName("Vietnam");
        countryRepository.save(country);

        country = new Country();
        country.setName("Wakanda");
        countryRepository.save(country);
    }

    public Country createNewCountry(Country country) {
        return countryRepository.save(country);
    }

    public Country updateCountry(Long id, Country country) {
        Optional<Country> countryUpdated = countryRepository.findById(id);

        if (!countryUpdated.isPresent()) {
            throw new CountryNotFoundException(id);
        } else {
            countryRepository.findById(id);

            return countryRepository.save(country);
        }
    }

    public void deleteCountry(Long id) {
        Optional<Country> countryOptional = countryRepository.findById(id);

        if (!countryOptional.isPresent()) {
            throw new CountryNotFoundException(id);
        } else {
            countryRepository.deleteById(id);
        }
    }
}
