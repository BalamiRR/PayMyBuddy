package com.balamir.paymybuddy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "sender")
    private Integer sender;

    @Column(name = "receiver")
    private Integer receiver;

    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "charge")
    private BigDecimal charge;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name ="status", nullable = false)
    private TransactionStatus status = TransactionStatus.INITIATED;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

}
