package com.pismo.assessment.infrastructure.persistence.mapper;

import com.pismo.assessment.domain.model.Account;
import com.pismo.assessment.infrastructure.persistence.entity.AccountEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountEntity toEntity(Account domain) {
        if (domain == null) {
            return null;
        }
        return AccountEntity.builder()
                .accountId(domain.getAccountId())
                .documentNumber(domain.getDocumentNumber())
                .build();
    }

    public Account toDomain(AccountEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Account(
                entity.getAccountId(),
                entity.getDocumentNumber());
    }
}
