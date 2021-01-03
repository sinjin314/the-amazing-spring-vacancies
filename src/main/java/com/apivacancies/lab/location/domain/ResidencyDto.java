/*
 * Copyright (c) 2021.
 * Abdelhadi Hasnaoui <abdelhadi.hasnaoui@protonmail.com>
 * sinjin.tech
 */

package com.apivacancies.lab.location.domain;

public class ResidencyDto {

    private String region;

    private String address;

    private Boolean spa;

    private Boolean pool;

    private Boolean nursery;

    private Residency.LocationType locType;

    private Country country;

    public ResidencyDto(String region, String address, Boolean spa, Boolean pool, Boolean nursery, Residency.LocationType locType, Country country) {
        this.region = region;
        this.address = address;
        this.spa = spa;
        this.pool = pool;
        this.nursery = nursery;
        this.locType = locType;
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getSpa() {
        return spa;
    }

    public void setSpa(Boolean spa) {
        this.spa = spa;
    }

    public Boolean getPool() {
        return pool;
    }

    public void setPool(Boolean pool) {
        this.pool = pool;
    }

    public Boolean getNursery() {
        return nursery;
    }

    public void setNursery(Boolean nursery) {
        this.nursery = nursery;
    }

    public Residency.LocationType getLocType() {
        return locType;
    }

    public void setLocType(Residency.LocationType locType) {
        this.locType = locType;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
