package com.pismo.assessment.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    @EqualsAndHashCode.Include
    private Long transactionId;

    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "operation_type_id", nullable = false)
    private Integer operationTypeId;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "event_date", nullable = false)
    private LocalDateTime eventDate;
}
