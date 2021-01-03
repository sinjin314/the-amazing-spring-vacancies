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

    @Query(value = "SELECT r FROM Residency r WHERE r.region = :region")
    List<Residency> findResidencyByRegion(String region);

    @Query(value = "SELECT r FROM Residency r WHERE r.pool = true")
    List<Residency> findResidencyWithPool();

    @Query(value = "SELECT r FROM Residency r WHERE r.locType = 'MOUNTAIN'")
    List<Residency> findResidencyAtMountain();
}
