package com.pismo.assessment.domain.model;

import com.pismo.assessment.domain.exception.InvalidOperationTypeException;

import java.math.BigDecimal;
import java.util.Arrays;

public enum OperationType {
    PURCHASE(1, "PURCHASE"),
    INSTALLMENT_PURCHASE(2, "INSTALLMENT PURCHASE"),
    WITHDRAWAL(3, "WITHDRAWAL"),
    PAYMENT(4, "PAYMENT");

    private final int id;
    private final String description;

    OperationType(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static OperationType fromId(Integer id) {
        if (id == null) {
            throw new InvalidOperationTypeException("Operation Type ID cannot be null");
        }
        return Arrays.stream(values())
                .filter(type -> type.id == id)
                .findFirst()
                .orElseThrow(() -> new InvalidOperationTypeException(id));
    }

    public BigDecimal applySignConversion(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
        BigDecimal absAmount = amount.abs();

        return this == PAYMENT ? absAmount : absAmount.negate();
    }
}
