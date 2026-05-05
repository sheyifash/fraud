package com.example.frauddetection.Service;

import com.example.frauddetection.Dao.FraudLogDao;
import com.example.frauddetection.Dto.TransactionRequest;
import com.example.frauddetection.Dto.TransactionResponse;
import com.example.frauddetection.Repo.BlacklistedMerchantsRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private BlacklistedMerchantsRepo blacklistRepo;

    @Mock
    private RateLimiterService rateLimiterService;

    @Mock
    private FraudLogDao fraudLogDAO;

    @InjectMocks
    private TransactionServiceImplementation transactionService;

    @Test
    void shouldFlagWhenMerchantIsBlacklisted() {
        TransactionRequest request = new TransactionRequest();
        request.setCardNo("9047");
        request.setAmount(new BigDecimal("5000"));
        request.setIpAddress("192.168.1.1");
        request.setMerchantName("Lmao ventures");

        when(blacklistRepo.existsByMerchantName("Lmao ventures")).thenReturn(true);

        TransactionResponse response = transactionService.sendPayment(request);

        assertTrue(response.isFlagged());
        assertEquals("Blacklisted merchant", response.getReason());
    }

    @Test
    void shouldFlagWhenAmountExceedsLimit() {
        TransactionRequest request = new TransactionRequest();
        request.setCardNo("9047");
        request.setAmount(new BigDecimal("500000000"));
        request.setIpAddress("192.168.1.1");
        request.setMerchantName("Lmao ventures");

        when(blacklistRepo.existsByMerchantName("Lmao ventures")).thenReturn(false);
        when(rateLimiterService.isRateLimited(any())).thenReturn(false);

        TransactionResponse response = transactionService.sendPayment(request);

        assertTrue(response.isFlagged());
        assertEquals("Transaction exceeds allowed limit", response.getReason());
    }
    @Test
    void shouldFlagWhenRateLimitExceeded() {

        RateLimiterImplementation limiter = new RateLimiterImplementation();

        String ip = "192.168.1.1";

        for (int i = 0; i < 6; i++) {
            limiter.isRateLimited(ip);
        }

        assertTrue(limiter.isRateLimited(ip));
    }
}