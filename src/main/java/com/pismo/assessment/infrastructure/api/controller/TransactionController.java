package com.pismo.assessment.infrastructure.api.controller;

import com.pismo.assessment.application.service.TransactionService;
import com.pismo.assessment.domain.model.Transaction;
import com.pismo.assessment.infrastructure.api.dto.CreateTransactionRequestDTO;
import com.pismo.assessment.infrastructure.api.dto.TransactionResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
@Tag(name = "Transactions", description = "Transaction management APIs")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    @Operation(summary = "Create a new transaction")
    public ResponseEntity<TransactionResponseDTO> create(@RequestBody @Valid CreateTransactionRequestDTO request) {
        var domain = new Transaction(
                request.accountId(),
                request.operationTypeId(),
                request.amount(),
                null
        );
        var created = transactionService.create(domain);
        var response = new TransactionResponseDTO(
                created.getTransactionId(),
                created.getAccountId(),
                created.getOperationTypeId(),
                created.getAmount(),
                created.getEventDate());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
