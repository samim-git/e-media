package com.sam.emedia.product.entities;

import jakarta.persistence.*;

import java.time.Instant;

//@Entity
public class BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "created")
    Instant created;

    @Column(name = "updated")
    Instant updated;
}
