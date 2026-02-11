package com.pismo.assessment.application.service;

import com.pismo.assessment.domain.exception.AccountNotFoundException;
import com.pismo.assessment.domain.exception.DuplicateDocumentException;
import com.pismo.assessment.domain.model.Account;
import com.pismo.assessment.infrastructure.persistence.mapper.AccountMapper;
import com.pismo.assessment.infrastructure.persistence.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Transactional
    public Account create(Account account) {
        log.info("Creating account with document number: {}", account.getDocumentNumber());

        if (accountRepository.existsByDocumentNumber(account.getDocumentNumber())) {
            log.warn("Account with document number {} already exists", account.getDocumentNumber());
            throw new DuplicateDocumentException(account.getDocumentNumber());
        }

        var entity = accountMapper.toEntity(account);
        var savedEntity = accountRepository.save(entity);

        log.info("Account created successfully with ID: {}", savedEntity.getAccountId());
        return accountMapper.toDomain(savedEntity);
    }

    @Transactional(readOnly = true)
    public Account findById(Long accountId) {
        log.info("Finding account by ID: {}", accountId);

        return accountRepository.findById(accountId)
                .map(accountMapper::toDomain)
                .orElseThrow(() -> {
                    log.error("Account not found with ID: {}", accountId);
                    return new AccountNotFoundException("Account not found with ID: " + accountId);
                });
    }
}
