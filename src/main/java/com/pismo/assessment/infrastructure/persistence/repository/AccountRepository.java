package com.pismo.assessment.infrastructure.persistence.repository;

import com.pismo.assessment.infrastructure.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    boolean existsByDocumentNumber(String documentNumber);
}
