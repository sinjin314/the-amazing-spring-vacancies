/*
 * Copyright (c) 2021.
 * Abdelhadi Hasnaoui <abdelhadi.hasnaoui@protonmail.com>
 * sinjin.tech
 */

package com.apivacancies.lab.location.repository;

import com.apivacancies.lab.location.domain.Residency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResidencyRepository extends JpaRepository<Residency, Long> {

    @Query(value = "select r FROM Residency r where r.country = :id")
    List<Residency> findResidencyByCountryId(Long id);
}
