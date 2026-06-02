package com.example.frauddetection.Controller;

import com.example.frauddetection.Dto.TransactionRequest;
import com.example.frauddetection.Dto.TransactionResponse;
import com.example.frauddetection.Service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fraudDetection")
public class TransactionController {
        private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @PostMapping("/sendpayment")
        public ResponseEntity<TransactionResponse> sendPayment(
              @Valid @RequestBody TransactionRequest request) {

            TransactionResponse response = transactionService.sendPayment(request);
            return ResponseEntity.ok(response);
        }
    }

