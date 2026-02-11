package com.pismo.assessment.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void shouldThrowExceptionForNullDocumentNumber() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Account(null));
        assertEquals("Document number cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForEmptyDocumentNumber() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Account(""));
        assertEquals("Document number cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForBlankDocumentNumber() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Account("   "));
        assertEquals("Document number cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldSetDocumentNumber() {
        Account account = new Account("12345678900");
        account.setDocumentNumber("98765432100");

        assertEquals("98765432100", account.getDocumentNumber());
    }

    @Test
    void shouldThrowExceptionWhenSettingNullDocumentNumber() {
        Account account = new Account("12345678900");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> account.setDocumentNumber(null));
        assertEquals("Document number cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenSettingBlankDocumentNumber() {
        Account account = new Account("12345678900");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> account.setDocumentNumber("  "));
        assertEquals("Document number cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldAllowValidDocumentNumberWithVariousFormats() {
        Account account1 = new Account("123.456.789-00");
        Account account2 = new Account("12345678900");
        Account account3 = new Account("ABC-123");

        assertEquals("123.456.789-00", account1.getDocumentNumber());
        assertEquals("12345678900", account2.getDocumentNumber());
        assertEquals("ABC-123", account3.getDocumentNumber());
    }
}
