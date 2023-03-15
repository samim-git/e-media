package com.sam.emedia.user.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "users", schema = "eMedia_db")
public class User implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "created")
    Instant created;

    @Column(name = "updated")
    Instant updated;
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

    /**NOTE: 1: customer, 2: manager, 3: employee"**/
    @Column(name = "user_type")
    private Integer userType;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses;
}
