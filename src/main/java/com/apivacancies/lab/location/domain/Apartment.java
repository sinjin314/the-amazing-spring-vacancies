/*
 * Copyright (c) 2021.
 * Abdelhadi Hasnaoui <abdelhadi.hasnaoui@protonmail.com>
 * sinjin.tech
 */

package com.apivacancies.lab.location.domain;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Apartment {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer bedding;

    private Integer surface;

    private Boolean babyEquipment;

    private Boolean clim;

    private Float price;

    public Boolean getClim() {
        return clim;
    }

    public void setClim(Boolean clim) {
        this.clim = clim;
    }

    public Long getId() {
        return id;
    }

    public Integer getBedding() {
        return bedding;
    }

    public void setBedding(Integer bedding) {
        this.bedding = bedding;
    }

    public Integer getSurface() {
        return surface;
    }

    public void setSurface(Integer surface) {
        this.surface = surface;
    }

    public Boolean getBabyEquipment() {
        return babyEquipment;
    }

    public void setBabyEquipment(Boolean babyEquipment) {
        this.babyEquipment = babyEquipment;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

//    public Long getResidency_id() {
//        return residency_id;
//    }
//
//    public void setResidency_id(Long residency_id) {
//        this.residency_id = residency_id;
//    }
}
