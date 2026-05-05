package com.example.frauddetection.Repo;

import com.example.frauddetection.Entity.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionLogRepo extends JpaRepository<TransactionModel, String> {
}
