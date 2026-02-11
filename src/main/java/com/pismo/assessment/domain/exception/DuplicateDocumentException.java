package com.pismo.assessment.domain.exception;

public class DuplicateDocumentException extends RuntimeException {

    public DuplicateDocumentException(String documentNumber) {
        super("An account with document number '" + documentNumber + "' already exists");
    }
}
