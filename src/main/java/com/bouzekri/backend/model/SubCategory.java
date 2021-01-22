package com.bouzekri.backend.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table
public @Data
class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int categoryId;

    @Column
    private String name;

    @Column
    private boolean isSelected = false;

    @Column
    private boolean isExpanded = true;
}