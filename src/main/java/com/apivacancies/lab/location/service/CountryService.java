/*
 * Copyright (c) 2021.
 * Abdelhadi Hasnaoui <abdelhadi.hasnaoui@protonmail.com>
 * sinjin.tech
 */

package com.apivacancies.lab.location.service;

import com.apivacancies.lab.location.domain.Country;
import com.apivacancies.lab.location.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
        private CountryRepository countryRepository;

        public CountryService(CountryRepository countryRepository)
        {
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

}
