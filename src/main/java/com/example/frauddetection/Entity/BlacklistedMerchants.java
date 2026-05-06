package com.example.frauddetection.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "blacklisted_merchants")
@Data
public class BlacklistedMerchants {
    @Id
    @Column(name = "merchant_id")
    private String merchantId;

    @Column(name = "merchant_name")
    private String merchantName;

    @Column(name = "suspicion_count")
    private int suspicionCount = 0;

    @Column(name = "black_listed_at")
    private LocalDateTime blackListedAt;
}
