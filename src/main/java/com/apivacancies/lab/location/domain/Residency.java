package com.apivacancies.lab.location.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Residency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String country;

    private String region;

    private String address;

    public enum LocationType implements Serializable {
        MOUNTAIN,
        OCEAN,
        SEA,
        CITY,
        COUNTRY
    }

    // @Column( columnDefinition = "enum('MOUNTAIN', 'OCEAN', 'SEA', 'CITY', 'COUNTRY)")
    @Enumerated(EnumType.STRING)
    private LocationType locType;

}
