package com.pismo.assessment.application.service;

import com.pismo.assessment.domain.exception.AccountNotFoundException;
import com.pismo.assessment.domain.exception.DuplicateDocumentException;
import com.pismo.assessment.domain.model.Account;
import com.pismo.assessment.infrastructure.persistence.entity.AccountEntity;
import com.pismo.assessment.infrastructure.persistence.mapper.AccountMapper;
import com.pismo.assessment.infrastructure.persistence.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountMapper accountMapper;

    @InjectMocks
    private AccountService accountService;

    @Test
    void shouldCreateAccountSuccessfully() {
        var account = new Account(null, "12345678900");
        var entity = AccountEntity.builder().accountId(1L).documentNumber("12345678900").build();

        when(accountRepository.existsByDocumentNumber(account.getDocumentNumber())).thenReturn(false);
        when(accountMapper.toEntity(account)).thenReturn(entity);
        when(accountRepository.save(entity)).thenReturn(entity);
        when(accountMapper.toDomain(entity)).thenReturn(account);

        var result = accountService.create(account);

        assertNotNull(result);
        assertEquals(account.getDocumentNumber(), result.getDocumentNumber());
        verify(accountRepository).save(entity);
    }

    @Test
    void shouldThrowExceptionWhenCreatingAccountWithDuplicateDocument() {
        var account = new Account(1L, "12345678900");

        when(accountRepository.existsByDocumentNumber(account.getDocumentNumber())).thenReturn(true);

        assertThrows(DuplicateDocumentException.class, () -> accountService.create(account));
        verify(accountRepository, never()).save(any());
    }

    @Test
    void shouldFindAccountByIdSuccessfully() {
        var accountId = 1L;
        var entity = AccountEntity.builder().accountId(accountId).documentNumber("12345678900").build();
        var account = new Account(accountId, "12345678900");

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(entity));
        when(accountMapper.toDomain(entity)).thenReturn(account);

        var result = accountService.findById(accountId);

        assertNotNull(result);
        assertEquals(accountId, result.getAccountId());
    }

    @Test
    void shouldThrowExceptionWhenAccountNotFound() {
        var accountId = 1L;

        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> accountService.findById(accountId));
    }
}
