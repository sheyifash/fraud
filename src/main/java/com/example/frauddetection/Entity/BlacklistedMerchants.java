package com.example.frauddetection.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class BlacklistedMerchants {
    @Id
    @Column
    private String merchantId;
    @Column
    private String merchantName;
    @Column
    private int suspicionCount = 0;
    @Column
    private LocalDateTime blackListedAt;
}