package com.pismo.assessment.application.service;

import com.pismo.assessment.domain.exception.AccountNotFoundException;
import com.pismo.assessment.domain.model.OperationType;
import com.pismo.assessment.domain.model.Transaction;
import com.pismo.assessment.infrastructure.persistence.entity.TransactionEntity;
import com.pismo.assessment.infrastructure.persistence.mapper.TransactionMapper;
import com.pismo.assessment.infrastructure.persistence.repository.AccountRepository;
import com.pismo.assessment.infrastructure.persistence.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionMapper transactionMapper;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    void shouldCreateTransactionSuccessfully() {
        var accountId = 1L;
        var amount = new BigDecimal("50.00");
        var transaction = new Transaction(accountId, OperationType.PURCHASE.getId(), amount, null);
        var entity = TransactionEntity.builder()
                .transactionId(1L)
                .accountId(accountId)
                .operationTypeId(OperationType.PURCHASE.getId())
                .amount(new BigDecimal("-50.00"))
                .eventDate(LocalDateTime.now())
                .build();

        var expectedDomain = new Transaction(1L, accountId, OperationType.PURCHASE.getId(),
                new BigDecimal("-50.00"), LocalDateTime.now());

        when(accountRepository.existsById(accountId)).thenReturn(true);
        when(transactionMapper.toEntity(any(Transaction.class))).thenReturn(entity);
        when(transactionRepository.save(entity)).thenReturn(entity);
        when(transactionMapper.toDomain(entity)).thenReturn(expectedDomain);

        var result = transactionService.create(transaction);

        assertNotNull(result);
        assertEquals(new BigDecimal("-50.00"), result.getAmount());
        assertNotNull(result.getEventDate());
        verify(transactionRepository).save(entity);
    }

    @Test
    void shouldThrowExceptionWhenCreatingTransactionForNonExistentAccount() {
        var transaction = new Transaction(99L, 1, new BigDecimal("50.00"), null);

        when(accountRepository.existsById(transaction.getAccountId())).thenReturn(false);

        assertThrows(AccountNotFoundException.class, () -> transactionService.create(transaction));
        verify(transactionRepository, never()).save(any());
    }

    @Test
    void shouldApplySignConversionLogic() {
        var accountId = 1L;
        var amount = new BigDecimal("50.00");
        var transaction = new Transaction(accountId, OperationType.PURCHASE.getId(), amount, null);

        when(accountRepository.existsById(accountId)).thenReturn(true);
        when(transactionMapper.toEntity(any(Transaction.class))).thenReturn(new TransactionEntity());
        when(transactionRepository.save(any())).thenReturn(new TransactionEntity());

        transactionService.create(transaction);

        assertEquals(new BigDecimal("-50.00"), transaction.getAmount());
    }
}
