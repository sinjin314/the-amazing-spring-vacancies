/*
 * Copyright (c) 2021.
 * Abdelhadi Hasnaoui <abdelhadi.hasnaoui@protonmail.com>
 * sinjin.tech
 */

package com.apivacancies.lab.location.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Apartment {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String type;

    private Long bedding;

    private Long surface;

    private Boolean babyEquipment;

    private Float price;

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getBedding() {
        return bedding;
    }

    public void setBedding(Long bedding) {
        this.bedding = bedding;
    }

    public Long getSurface() {
        return surface;
    }

    public void setSurface(Long surface) {
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
