package com.example.frauddetection.Controller;

import com.example.frauddetection.Dao.FraudLogDao;
import com.example.frauddetection.Service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/flagged")
    public ResponseEntity<List<Map<String, Object>>> getFlaggedTransactions() {
        return ResponseEntity.ok(adminService.getFlaggedTransactions());
    }
}