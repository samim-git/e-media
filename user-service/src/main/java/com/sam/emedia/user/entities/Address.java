package com.sam.emedia.user.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "addresses", schema = "eMedia_db")
public class Address {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "created")
    Instant created;

    @Column(name = "updated")
    Instant updated;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;
    //@Getter(AccessLevel.NONE)
    @Column(name = "zipCode")
    private String zipCode;
    @Column(name = "street")
    private String street;
    @Column(name = "houseNo")
    private String houseNo;

    @Column(name = "isDefaultAddress")
    boolean isDefaultAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;
}
