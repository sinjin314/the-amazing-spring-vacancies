package com.apivacancies.lab.location.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class apartment {

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

}
