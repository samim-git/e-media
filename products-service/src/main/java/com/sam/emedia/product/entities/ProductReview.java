package com.sam.emedia.product.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "productReviews", schema = "eMedia_products_db", catalog = "")
public class ProductReview {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "created")
    Instant created = Instant.now();

    @Column(name = "updated")
    Instant updated = Instant.now();
    @Column(name = "comment")
    private String comment;
    @Column(name = "rate")
    private double rate;

    @Column(name = "userId")
    int userId;

    @ManyToOne(fetch = FetchType.LAZY)
            @JoinColumn(name = "productId")
    Product product;
}

