package com.pismo.assessment.application.service;

import com.pismo.assessment.domain.exception.AccountNotFoundException;
import com.pismo.assessment.domain.model.OperationType;
import com.pismo.assessment.domain.model.Transaction;
import com.pismo.assessment.infrastructure.persistence.mapper.TransactionMapper;
import com.pismo.assessment.infrastructure.persistence.repository.AccountRepository;
import com.pismo.assessment.infrastructure.persistence.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final TransactionMapper transactionMapper;

    @Transactional
    public Transaction create(Transaction transaction) {
        log.info("Creating transaction for account ID: {}", transaction.getAccountId());

        if (!accountRepository.existsById(transaction.getAccountId())) {
            log.error("Account not found with ID: {}", transaction.getAccountId());
            throw new AccountNotFoundException("Account not found with ID: " + transaction.getAccountId());
        }

        var operationType = OperationType.fromId(transaction.getOperationTypeId());
        var finalAmount = operationType.applySignConversion(transaction.getAmount());

        transaction.setAmount(finalAmount);
        transaction.setEventDate(LocalDateTime.now());

        var entity = transactionMapper.toEntity(transaction);
        var savedEntity = transactionRepository.save(entity);

        log.info("Transaction created successfully with ID: {}", savedEntity.getTransactionId());
        return transactionMapper.toDomain(savedEntity);
    }
}
