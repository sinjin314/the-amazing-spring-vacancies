/*
 * Copyright (c) 2021.
 * Abdelhadi Hasnaoui <abdelhadi.hasnaoui@protonmail.com>
 * sinjin.tech
 */

package com.apivacancies.lab.location.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Residency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String region;

    private String address;

    private Boolean spa;

    private Boolean pool;

    private Boolean nursery;

    public enum LocationType implements Serializable {
        MOUNTAIN,
        OCEAN,
        SEA,
        CITY,
        COUNTRY
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Apartment> apartments;

    @ManyToOne
    @JoinColumn(name = "country_id", foreignKey = @ForeignKey(name = "Country_id_Residency"))
    private Country country;

    @Enumerated(EnumType.STRING)
    private LocationType locType;



    public Long getId() {
        return id;
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

    public Set<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(Set<Apartment> apartments) {
        this.apartments = apartments;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public LocationType getLocType() {
        return locType;
    }

    public void setLocType(LocationType locType) {
        this.locType = locType;
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

}
