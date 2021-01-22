package com.bouzekri.backend.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table
public @Data class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int categoryId;

    @Column
    private int subCategoryId;

    @Column
    private String name;

    @Column
    private double unitPrice;

    @Lob
    @Column(length = Integer.MAX_VALUE, nullable = true)
    private byte[] url;
}
