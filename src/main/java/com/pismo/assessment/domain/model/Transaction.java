package com.pismo.assessment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
    private Long transactionId;
    private Long accountId;
    private Integer operationTypeId;
    private BigDecimal amount;
    private LocalDateTime eventDate;

    public Transaction(Long accountId, Integer operationTypeId, BigDecimal amount, LocalDateTime eventDate) {
        this.accountId = accountId;
        this.operationTypeId = operationTypeId;
        this.amount = amount;
        this.eventDate = eventDate;
    }
}
