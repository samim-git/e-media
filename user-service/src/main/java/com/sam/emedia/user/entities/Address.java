package com.sam.emedia.user.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "addresses", schema = "eMedia_db")
public class Address implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "created")
    Instant created = Instant.now();

    @Column(name = "updated")
    Instant updated = Instant.now();
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
    boolean defaultAddress = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    @Getter(AccessLevel.NONE)
    private User user;
}
