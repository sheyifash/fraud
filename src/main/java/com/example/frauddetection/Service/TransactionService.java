package com.example.frauddetection.Service;

import com.example.frauddetection.Dto.TransactionRequest;
import com.example.frauddetection.Dto.TransactionResponse;

public interface TransactionService{
    public TransactionResponse sendPayment(TransactionRequest request);
}
