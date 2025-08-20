package com.balamir.paymybuddy.model.dto;

import com.balamir.paymybuddy.model.Transaction;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class TransactionDto {
    private Integer id;
    private String otherPartyName;
    private BigDecimal amount;
    private String description;
    private Instant createdAt;

    public TransactionDto(Transaction txn, Integer currentUserId) {
        this.id = txn.getId();

        if (txn.getReceiver().getId().equals(currentUserId)) {
            this.otherPartyName = txn.getSender().getUserName();
        } else {
            this.otherPartyName = txn.getReceiver().getUserName();
        }
        this.amount = txn.getAmount();
        this.description = txn.getDescription();
        this.createdAt = txn.getCreatedAt();
    }
}
