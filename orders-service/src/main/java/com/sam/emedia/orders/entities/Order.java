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
@Table(name = "orders", schema = "eMedia_orders_db", catalog = "")
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "created")
    Instant created = Instant.now();

    @Column(name = "updated")
    Instant updated = Instant.now();

    @Column(name = "userId")
    int userId;
    @Column(name = "status")
    //"1: ordered, 2 canceled, 3 completed"
    int status = 1;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bucketId")
    Bucket bucket;
}

