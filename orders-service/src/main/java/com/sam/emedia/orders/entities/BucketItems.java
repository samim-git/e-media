package com.sam.emedia.orders.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bucketItems", schema = "eMedia_orders_db", catalog = "")
public class BucketItems {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "created")
    Instant created = Instant.now();

    @Column(name = "updated")
    Instant updated = Instant.now();
    @Column(name = "productCount")
    private int productCount;
    @Column(name = "productId")
    private int productId;

    @ManyToOne(fetch = FetchType.LAZY)
            @JoinColumn(name = "bucketId")
    Bucket bucket;
}

