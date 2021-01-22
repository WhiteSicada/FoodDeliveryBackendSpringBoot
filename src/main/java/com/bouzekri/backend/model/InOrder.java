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
    private String placed_order_id;

    @NotBlank
    @Column
    private String menu_item_id;

    @NotBlank
    @Column
    private String quantity;

    @Column
    private String item_price;

    @Column
    private String price;

}
