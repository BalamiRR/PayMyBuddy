package com.balamir.paymybuddy.model.dto;

import com.balamir.paymybuddy.model.Transaction;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;

@Getter
@Setter
public class TransactionDto {
    private Integer id;
    private String userName;
    private BigDecimal amount;
    private String description;
    private Instant createdAt;
    private BigDecimal netAmount;
    private String direction; // OUTGOING and INCOMING arrows

    public TransactionDto(Transaction transaction, Integer currentUserId) {
        this.id = transaction.getId();
        if (transaction.getReceiver().getId().equals(currentUserId)) {
            this.userName = transaction.getSender().getUserName();
        } else {
            this.userName = transaction.getReceiver().getUserName();
        }
        this.amount = transaction.getAmount();
        this.description = transaction.getDescription();
        this.createdAt = transaction.getCreatedAt();
        BigDecimal feeRate = new BigDecimal("0.005");
        this.netAmount = transaction.getAmount()
                .subtract(transaction.getAmount().multiply(feeRate))
                .setScale(2, RoundingMode.HALF_UP);

        this.direction = transaction.getSender().getId().equals(currentUserId) ? "OUTGOING" : "INCOMING";
    }
}
