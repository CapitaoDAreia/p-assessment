package com.pismo.assessment.infrastructure.persistence.repository;

import com.pismo.assessment.infrastructure.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
