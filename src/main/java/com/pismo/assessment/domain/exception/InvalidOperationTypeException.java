package com.pismo.assessment.domain.exception;

public class InvalidOperationTypeException extends RuntimeException {

    public InvalidOperationTypeException(Integer operationTypeId) {
        super("Invalid operation type ID: " + operationTypeId + ". Valid operation type IDs are 1, 2, 3, or 4");
    }

    public InvalidOperationTypeException(String message) {
        super(message);
    }
}
