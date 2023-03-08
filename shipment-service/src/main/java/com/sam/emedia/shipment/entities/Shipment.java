package com.sam.emedia.shipment.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shipments", schema = "eMedia_shipments_db", catalog = "")
public class Shipment {
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

    @Column(name = "shipperId")
    int shipperId = 0;
    @Column(name = "orderId")
    int orderId;
    @Column(name = "status")
    int status = 1;
    //"1: created, 2 processed, 3 delivered"

    @OneToMany(mappedBy = "shipment")
    List<ShipmentHistory> shipmentHistories;
}

