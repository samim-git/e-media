package com.sam.emedia.user.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@Entity
@Table(name = "users", schema = "eMedia_db", catalog = "")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    //@Getter(AccessLevel.NONE)
    @Column(name = "password")
    private String password;
    @Column(name = "profile")
    private String profile = "";
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;
    @Column(name = "created")
    private Instant created = Instant.now();
    @Column(name = "user_type")
    private Integer userType;
}
