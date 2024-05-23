package com.example.demojasperreport.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "PAYMENT_DETAILS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long paymentId;
    private long bookingId;
    private long amount;

    @Column(name = "MODE")
    private String paymentMode;
    private Instant paymentDate;
    private String paymentStatus;
    private String referenceNumber;
}
