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
    private String direction; // "OUTGOING", "INCOMING" arrows


    public TransactionDto(Transaction transaction, Integer currentUserId) {
        this.id = transaction.getId();

        if (transaction.getReceiver().getId().equals(currentUserId)) {
            this.otherPartyName = transaction.getSender().getUserName();
        } else {
            this.otherPartyName = transaction.getReceiver().getUserName();
        }
        this.amount = transaction.getAmount();
        this.description = transaction.getDescription();
        this.createdAt = transaction.getCreatedAt();
        this.direction = transaction.getSender().getId() == currentUserId ? "OUTGOING" : "INCOMING";
    }
}
