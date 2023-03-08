package com.sam.emedia.orders.entities;

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
@Table(name = "buckets", schema = "eMedia_orders_db", catalog = "")
public class Bucket {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "created")
    Instant created = Instant.now();

    @Column(name = "updated")
    Instant updated = Instant.now();
    @Column(name = "name")
    private String name;
    @Column(name = "userId")
    private int userId;

    @Column(name = "items")
    private int[] items;

    @OneToMany(mappedBy = "bucket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BucketItems> bucketItems;

    @OneToOne(mappedBy = "bucket", cascade = CascadeType.ALL, orphanRemoval = true)
    Order order;
}

