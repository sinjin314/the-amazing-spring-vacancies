/*
 * Copyright (c) 2021.
 * Abdelhadi Hasnaoui <abdelhadi.hasnaoui@protonmail.com>
 * sinjin.tech
 */

package com.apivacancies.lab.location.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings;

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

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }


    public void addBooking(Booking booking) {
        this.bookings.add(booking);
    }


}
