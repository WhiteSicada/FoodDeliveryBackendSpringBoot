package com.bouzekri.backend.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table
public @Data
class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column
    private String name;

    @Lob
    @Column(length = Integer.MAX_VALUE, nullable = true)
    private byte[] resourceId;

}