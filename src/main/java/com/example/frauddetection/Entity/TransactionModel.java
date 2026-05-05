package com.example.frauddetection.Entity;

import com.example.frauddetection.Enum.TransactionStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Value;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class TransactionModel {
    @Column
    private LocalDateTime createdAt;
    @Column
    private BigDecimal amount;
    @Id
    private String transId = createdAt + UUID.randomUUID().toString().substring(0, 9) + amount;
    @Column
    private String merchantId;
    @Column
    private String tokenizedCardNo;
    @Column
    private String merchantName;
    @Column
    private String maskedIpAddress;
    @Column
    private TransactionStatus status;

}
