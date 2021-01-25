package com.bouzekri.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table
public @Data
class PlacedOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String restaurantId;

    @Column
    private String customerId;

    @Column
    private Instant orderTime;

    @Column
    private String estimatedDeliveryTime;

    @Column
    private String deliveryAddress;

    @Column
    private int price;

    @Column
    private String status;

}
