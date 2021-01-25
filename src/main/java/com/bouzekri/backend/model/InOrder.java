package com.bouzekri.backend.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Table
public @Data class InOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column
    private String placedOrderId;

    @NotBlank
    @Column
    private String menuItemId;

    @NotBlank
    @Column
    private String quantity;

    @Column
    private String itemPrice;

    @Column
    private String price;

}
