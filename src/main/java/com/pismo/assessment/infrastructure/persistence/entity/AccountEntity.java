package com.pismo.assessment.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", columnDefinition = "BIGINT")
    @EqualsAndHashCode.Include
    private Long accountId;

    @Column(name = "document_number", nullable = false, unique = true)
    private String documentNumber;
}
