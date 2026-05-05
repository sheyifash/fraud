package com.example.frauddetection.Repo;

import com.example.frauddetection.Entity.BlacklistedMerchants;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlacklistedMerchantsRepo extends JpaRepository <BlacklistedMerchants, String> {
    boolean existsByMerchantName(@NotBlank(message = "merchant name is required") String merchantName);
}
