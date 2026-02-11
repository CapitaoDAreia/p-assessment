package com.pismo.assessment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Long accountId;
    private String documentNumber;

    public Account(String documentNumber) {
        validateDocumentNumber(documentNumber);
        this.documentNumber = documentNumber;
    }

    private void validateDocumentNumber(String documentNumber) {
        if (documentNumber == null || documentNumber.isBlank()) {
            throw new IllegalArgumentException("Document number cannot be null or empty");
        }
    }

    public void setDocumentNumber(String documentNumber) {
        validateDocumentNumber(documentNumber);
        this.documentNumber = documentNumber;
    }
}
