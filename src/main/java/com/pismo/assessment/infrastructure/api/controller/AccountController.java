package com.pismo.assessment.infrastructure.api.controller;

import com.pismo.assessment.application.service.AccountService;
import com.pismo.assessment.domain.model.Account;
import com.pismo.assessment.infrastructure.api.dto.AccountResponseDTO;
import com.pismo.assessment.infrastructure.api.dto.CreateAccountRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
@Tag(name = "Accounts", description = "Account management APIs")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    @Operation(summary = "Create a new account")
    public ResponseEntity<AccountResponseDTO> create(@RequestBody @Valid CreateAccountRequestDTO request) {
        var domain = new Account(request.documentNumber());
        var created = accountService.create(domain);
        var response = new AccountResponseDTO(
                created.getAccountId(),
                created.getDocumentNumber());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{accountId}")
    @Operation(summary = "Get account by ID")
    public ResponseEntity<AccountResponseDTO> getById(@PathVariable Long accountId) {
        var account = accountService.findById(accountId);
        var response = new AccountResponseDTO(
                account.getAccountId(),
                account.getDocumentNumber());

        return ResponseEntity.ok(response);
    }
}
