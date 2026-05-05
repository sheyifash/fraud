package com.example.frauddetection.Service;

import com.example.frauddetection.Dao.FraudLogDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdminService {

    private final FraudLogDao fraudLogDao;

    public AdminService(FraudLogDao fraudLogDao) {
        this.fraudLogDao = fraudLogDao;
    }

    public List<Map<String, Object>> getFlaggedTransactions() {
        return fraudLogDao.getFlaggedTransactions();
    }
}
