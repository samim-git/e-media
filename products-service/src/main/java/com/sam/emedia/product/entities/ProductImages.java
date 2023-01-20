package com.sam.emedia.product.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_images", schema = "eMedia_db")
public class ProductImages {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "created")
    Instant created;

    @Column(name = "updated")
    Instant updated;
    @Column(name = "name")
    String name;

    @ManyToOne
    Product product;
}
