package com.example.frauddetection.Entity;

import com.example.frauddetection.Enum.TransactionStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction_model")
@Data
public class TransactionModel {

    @Id
    @Column(name = "trans_id")
    private String transId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "merchant_id")
    private String merchantId;

    @Column(name = "tokenized_card_no")
    private String tokenizedCardNo;

    @Column(name = "merchant_name")
    private String merchantName;

    @Column(name = "masked_ip_address")
    private String maskedIpAddress;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
}
