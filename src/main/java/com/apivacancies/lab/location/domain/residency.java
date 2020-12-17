package com.apivacancies.lab.location.domain;

import com.apivacancies.lab.Gps;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class residency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String country;

    private String region;

    private String address;

    // TODO : check a good method for the Gps implementation
    // private Gps position;

}
