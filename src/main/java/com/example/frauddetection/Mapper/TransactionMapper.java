package com.example.frauddetection.Mapper;

import com.example.frauddetection.Dto.TransactionRequest;
import com.example.frauddetection.Dto.TransactionResponse;
import com.example.frauddetection.Entity.TransactionModel;

public class TransactionMapper {

    public TransactionModel mapToEntity(TransactionRequest request){
        TransactionModel transaction = new TransactionModel();
        transaction.setAmount(request.getAmount());
        transaction.setMerchantName(request.getMerchantName());
        transaction.setTokenizedCardNo(request.getCardNo());
        return transaction;
    }
    public TransactionResponse mapToDto(TransactionModel transaction){
        return TransactionResponse.builder()
                .transactionId(transaction.getTransId())
                .status(transaction.getStatus())
                .amount(transaction.getAmount())
                .merchantName(transaction.getMerchantName())
                .createdAt(transaction.getCreatedAt())
                .build();

    }
}
