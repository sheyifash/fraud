package com.example.frauddetection.Repo;

import com.example.frauddetection.Entity.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepo extends JpaRepository<TransactionModel, String> {
}
