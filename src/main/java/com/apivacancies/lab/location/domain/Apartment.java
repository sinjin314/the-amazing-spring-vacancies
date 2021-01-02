package com.apivacancies.lab.location.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Apartment {

    public Long getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String type;

    private Long bedding;

    private Long surface;

    private Boolean babyEquipment;

    private Float price;

    private Long residency_id;

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

    public Long getResidency_id() {
        return residency_id;
    }

    public void setResidency_id(Long residency_id) {
        this.residency_id = residency_id;
    }
}
