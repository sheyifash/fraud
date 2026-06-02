package com.example.frauddetection.Controller;

import com.example.frauddetection.Dao.FraudLogDao;
import com.example.frauddetection.Service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/flagged")
    public ResponseEntity<List<Map<String, Object>>> getFlaggedTransactions() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Controller: auth=" + auth);
        if (auth != null) {
            System.out.println("Controller: authorities=" + auth.getAuthorities());
        }
        System.out.println(adminService.getFlaggedTransactions());
        return ResponseEntity.ok(adminService.getFlaggedTransactions());
    }
}