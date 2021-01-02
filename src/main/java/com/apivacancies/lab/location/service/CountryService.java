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

@Service
public class CountryService {
        private CountryRepository countryRepository;

        public CountryService(CountryRepository countryRepository)
        {
            this.countryRepository = countryRepository;
        }

        public List<Country> getCountry() {
            return countryRepository.findAll();
        }

}
