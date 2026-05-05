package com.example.frauddetection.Dto;

import com.example.frauddetection.Enum.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class TransactionResponse {
    private String transactionId;
    private BigDecimal amount;
    private String merchantName;
    private boolean flagged;
    private String reason;
    private TransactionStatus status;
    private LocalDateTime createdAt;

    public TransactionResponse() {

    }
}
