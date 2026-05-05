package com.example.frauddetection.Dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Valid
public class TransactionRequest {
    @NotBlank (message = "Card No. is required")
    private String cardNo;
    @NotBlank (message = "amount is required")
    private BigDecimal amount;
    @NotBlank (message = "merchant name is required")
    private String merchantName;
    @NotNull (message = "ipAddress cannot be empty")
    private String ipAddress;
}
