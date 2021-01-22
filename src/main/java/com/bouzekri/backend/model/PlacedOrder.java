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
    private String restaurant_id;

    @Column
    private String customer_id;

    @Column
    private Instant order_time;

    @Column
    private String estimated_delivery_time;

    @Column
    private String delivery_address;

    @Column
    private int price;

    @Column
    private String status;

}
