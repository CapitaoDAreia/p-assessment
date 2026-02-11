package com.pismo.assessment.domain.model;

import com.pismo.assessment.domain.exception.InvalidOperationTypeException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class OperationTypeTest {

    @Test
    void shouldReturnCorrectIdForEachOperationType() {
        assertEquals(1, OperationType.PURCHASE.getId());
        assertEquals(2, OperationType.INSTALLMENT_PURCHASE.getId());
        assertEquals(3, OperationType.WITHDRAWAL.getId());
        assertEquals(4, OperationType.PAYMENT.getId());
    }

    @Test
    void shouldReturnCorrectDescriptionForEachOperationType() {
        assertEquals("PURCHASE", OperationType.PURCHASE.getDescription());
        assertEquals("INSTALLMENT PURCHASE", OperationType.INSTALLMENT_PURCHASE.getDescription());
        assertEquals("WITHDRAWAL", OperationType.WITHDRAWAL.getDescription());
        assertEquals("PAYMENT", OperationType.PAYMENT.getDescription());
    }

    @Test
    void shouldFindOperationTypeById() {
        assertEquals(OperationType.PURCHASE, OperationType.fromId(1));
        assertEquals(OperationType.INSTALLMENT_PURCHASE, OperationType.fromId(2));
        assertEquals(OperationType.WITHDRAWAL, OperationType.fromId(3));
        assertEquals(OperationType.PAYMENT, OperationType.fromId(4));
    }

    @Test
    void shouldApplyNegativeSignForPurchase() {
        BigDecimal amount = new BigDecimal("50.00");
        BigDecimal result = OperationType.PURCHASE.applySignConversion(amount);

        assertEquals(new BigDecimal("-50.00"), result);
        assertTrue(result.compareTo(BigDecimal.ZERO) < 0);
    }

    @Test
    void shouldApplyNegativeSignForInstallmentPurchase() {
        BigDecimal amount = new BigDecimal("100.00");
        BigDecimal result = OperationType.INSTALLMENT_PURCHASE.applySignConversion(amount);

        assertEquals(new BigDecimal("-100.00"), result);
        assertTrue(result.compareTo(BigDecimal.ZERO) < 0);
    }

    @Test
    void shouldApplyNegativeSignForWithdrawal() {
        BigDecimal amount = new BigDecimal("75.50");
        BigDecimal result = OperationType.WITHDRAWAL.applySignConversion(amount);

        assertEquals(new BigDecimal("-75.50"), result);
        assertTrue(result.compareTo(BigDecimal.ZERO) < 0);
    }

    @Test
    void shouldApplyPositiveSignForPayment() {
        BigDecimal amount = new BigDecimal("60.00");
        BigDecimal result = OperationType.PAYMENT.applySignConversion(amount);

        assertEquals(new BigDecimal("60.00"), result);
        assertTrue(result.compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    void shouldThrowExceptionForNullAmount() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> OperationType.PURCHASE.applySignConversion(null));
        assertEquals("Amount cannot be null", exception.getMessage());
    }

    @Test
    void shouldHandleDecimalPrecision() {
        BigDecimal amount = new BigDecimal("123.45");
        BigDecimal result = OperationType.PURCHASE.applySignConversion(amount);

        assertEquals(new BigDecimal("-123.45"), result);
        assertEquals(2, result.scale());
    }

    @Test
    void shouldEnsureNegativeSignEvenIfInputIsAlreadyNegativeForDebitOperations() {
        BigDecimal negativeInput = new BigDecimal("-50.00");

        BigDecimal result = OperationType.PURCHASE.applySignConversion(negativeInput);

        assertEquals(new BigDecimal("-50.00"), result);
        assertTrue(result.compareTo(BigDecimal.ZERO) < 0);
    }

    @Test
    void shouldThrowExceptionForInvalidOperationTypeId() {
        InvalidOperationTypeException exception = assertThrows(InvalidOperationTypeException.class,
                () -> OperationType.fromId(99));

        assertTrue(exception.getMessage().contains("99"));
    }

    @Test
    void shouldThrowExceptionForNullOperationTypeId() {
        InvalidOperationTypeException exception = assertThrows(InvalidOperationTypeException.class,
                () -> OperationType.fromId(null));

        assertEquals("Operation Type ID cannot be null", exception.getMessage());
    }
}
