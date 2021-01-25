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
    private String placedOrderId;

    @Column
    private String customerId;

    @Column
    private String commentText;

    @Column
    private Instant ts;

    @Column
    private Boolean isComplaint;

    @Column
    private Boolean isPraise;


}