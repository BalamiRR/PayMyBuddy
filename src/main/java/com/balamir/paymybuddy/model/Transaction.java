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

    @ManyToOne
    @JoinColumn(name = "sender", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver", nullable = false)
    private User receiver;

    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "amount", precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(name = "charge", precision = 19, scale = 2)
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
