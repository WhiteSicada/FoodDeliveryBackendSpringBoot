package com.bouzekri.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;


@Entity
@Table
public @Data
class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String placed_order_id;

    @Column
    private String customer_id;

    @Column
    private String comment_text;

    @Column
    private Instant ts;

    @Column
    private Boolean is_complaint;

    @Column
    private Boolean is_praise;


}