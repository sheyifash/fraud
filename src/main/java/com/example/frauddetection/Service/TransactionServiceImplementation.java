package com.example.frauddetection.Service;

import com.example.frauddetection.Dao.FraudLogDao;
import com.example.frauddetection.Dto.TransactionRequest;
import com.example.frauddetection.Dto.TransactionResponse;
import com.example.frauddetection.Enum.TransactionStatus;
import com.example.frauddetection.Repo.BlacklistedMerchantsRepo;
import com.example.frauddetection.Repo.TransactionRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TransactionServiceImplementation implements TransactionService {
//    private final long transactionLimit;
    private final TransactionRepo transactionRepo;
    private final BlacklistedMerchantsRepo blacklistedMerchantsRepo;
    private final RateLimiterService rateLimiterService;
    private final FraudLogDao fraudLogDao;

    public TransactionServiceImplementation(TransactionRepo transactionRepo, BlacklistedMerchantsRepo blacklistedMerchantsRepo, RateLimiterService rateLimiterService, FraudLogDao fraudLogDao) {
        this.transactionRepo = transactionRepo;
        this.blacklistedMerchantsRepo = blacklistedMerchantsRepo;
        this.rateLimiterService = rateLimiterService;
        this.fraudLogDao = fraudLogDao;
    }

    @Override
    public TransactionResponse sendPayment(TransactionRequest request) {
        boolean flagged = false;
        String reason = "Transaction and merchant are okay";
        BigDecimal transactionLimit = new BigDecimal(100000000);

        if (blacklistedMerchantsRepo.existsByMerchantName(request.getMerchantName())) {
            flagged = true;
            reason = "Blacklisted merchant";
        } else if (rateLimiterService.isRateLimited(request.getIpAddress())) {
            flagged = true;
            reason = "Too many requests from this IP";
        }
        else if (request.getAmount().compareTo(transactionLimit) > 0) {
            flagged = true;
            reason = "Transaction exceeds allowed limit";
        }
        TransactionResponse response = new TransactionResponse();
        response.setTransactionId(UUID.randomUUID().toString());
        response.setAmount(request.getAmount());
        response.setMerchantName(request.getMerchantName()); // replace later if you fetch real name
        response.setFlagged(flagged);
        response.setReason(reason);
        response.setStatus(flagged ? TransactionStatus.FAILED : TransactionStatus.SUCCESS);
        response.setCreatedAt(LocalDateTime.now());

        // ⚡ 4. Log transaction
        fraudLogDao.saveLog(request, flagged);

        return response;
    }
}