/*
 * Copyright (c) 2021.
 * Abdelhadi Hasnaoui <abdelhadi.hasnaoui@protonmail.com>
 * sinjin.tech
 */

package com.apivacancies.lab.location.repository;

import com.apivacancies.lab.location.domain.Residency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidencyRepository extends JpaRepository<Residency, Long> {
}
