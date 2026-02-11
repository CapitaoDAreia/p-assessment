package com.pismo.assessment.infrastructure.persistence.mapper;

import com.pismo.assessment.domain.model.Transaction;
import com.pismo.assessment.infrastructure.persistence.entity.TransactionEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TransactionMapperTest {

    private TransactionMapper transactionMapper;

    @BeforeEach
    void setUp() {
        transactionMapper = new TransactionMapper();
    }

    @Test
    void shouldMapDomainToEntity() {
        LocalDateTime eventDate = LocalDateTime.now();
        Transaction domain = new Transaction(1L, 1L, 1, new BigDecimal("-50.00"), eventDate);

        TransactionEntity entity = transactionMapper.toEntity(domain);

        assertNotNull(entity);
        assertEquals(domain.getTransactionId(), entity.getTransactionId());
        assertEquals(domain.getAccountId(), entity.getAccountId());
        assertEquals(domain.getOperationTypeId(), entity.getOperationTypeId());
        assertEquals(domain.getAmount(), entity.getAmount());
        assertEquals(domain.getEventDate(), entity.getEventDate());
    }

    @Test
    void shouldMapEntityToDomain() {
        LocalDateTime eventDate = LocalDateTime.now();
        TransactionEntity entity = TransactionEntity.builder()
                .transactionId(1L)
                .accountId(2L)
                .operationTypeId(3)
                .amount(new BigDecimal("-75.50"))
                .eventDate(eventDate)
                .build();

        Transaction domain = transactionMapper.toDomain(entity);

        assertNotNull(domain);
        assertEquals(entity.getTransactionId(), domain.getTransactionId());
        assertEquals(entity.getAccountId(), domain.getAccountId());
        assertEquals(entity.getOperationTypeId(), domain.getOperationTypeId());
        assertEquals(entity.getAmount(), domain.getAmount());
        assertEquals(entity.getEventDate(), domain.getEventDate());
    }

    @Test
    void shouldReturnNullWhenMappingNullDomainToEntity() {
        assertNull(transactionMapper.toEntity(null));
    }

    @Test
    void shouldReturnNullWhenMappingNullEntityToDomain() {
        assertNull(transactionMapper.toDomain(null));
    }
}
