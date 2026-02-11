package com.pismo.assessment.infrastructure.persistence.mapper;

import com.pismo.assessment.domain.model.Transaction;
import com.pismo.assessment.infrastructure.persistence.entity.TransactionEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransactionEntity toEntity(Transaction domain) {
        if (domain == null) {
            return null;
        }
        return TransactionEntity.builder()
                .transactionId(domain.getTransactionId())
                .accountId(domain.getAccountId())
                .operationTypeId(domain.getOperationTypeId())
                .amount(domain.getAmount())
                .eventDate(domain.getEventDate())
                .build();
    }

    public Transaction toDomain(TransactionEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Transaction(
                entity.getTransactionId(),
                entity.getAccountId(),
                entity.getOperationTypeId(),
                entity.getAmount(),
                entity.getEventDate());
    }
}
