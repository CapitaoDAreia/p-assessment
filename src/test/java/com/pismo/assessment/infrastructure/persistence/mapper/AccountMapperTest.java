package com.pismo.assessment.infrastructure.persistence.mapper;

import com.pismo.assessment.domain.model.Account;
import com.pismo.assessment.infrastructure.persistence.entity.AccountEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountMapperTest {

    private AccountMapper accountMapper;

    @BeforeEach
    void setUp() {
        accountMapper = new AccountMapper();
    }

    @Test
    void shouldMapDomainToEntity() {
        Account domain = new Account(1L, "12345678900");

        AccountEntity entity = accountMapper.toEntity(domain);

        assertNotNull(entity);
        assertEquals(domain.getAccountId(), entity.getAccountId());
        assertEquals(domain.getDocumentNumber(), entity.getDocumentNumber());
    }

    @Test
    void shouldMapEntityToDomain() {
        AccountEntity entity = AccountEntity.builder()
                .accountId(1L)
                .documentNumber("12345678900")
                .build();

        Account domain = accountMapper.toDomain(entity);

        assertNotNull(domain);
        assertEquals(entity.getAccountId(), domain.getAccountId());
        assertEquals(entity.getDocumentNumber(), domain.getDocumentNumber());
    }

    @Test
    void shouldReturnNullWhenMappingNullDomainToEntity() {
        assertNull(accountMapper.toEntity(null));
    }

    @Test
    void shouldReturnNullWhenMappingNullEntityToDomain() {
        assertNull(accountMapper.toDomain(null));
    }
}
