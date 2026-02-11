package com.pismo.assessment.infrastructure.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record CreateAccountRequestDTO(
        @NotBlank(message = "Document number is required") @JsonProperty("document_number") String documentNumber) {
}
