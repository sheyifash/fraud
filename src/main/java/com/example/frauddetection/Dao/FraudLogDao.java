package com.example.frauddetection.Dao;

import com.example.frauddetection.Dto.TransactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class FraudLogDao {
    private final JdbcTemplate jdbcTemplate;

    public void saveLog(TransactionRequest request, boolean flagged) {

        String sql = """
            INSERT INTO fraud_logs (card_no, amount, merchant_id, ip_address, flagged)
            VALUES (?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(sql,
                request.getCardNo(),
                request.getAmount(),
                request.getMerchantName(),
                request.getIpAddress(),
                flagged
        );
    }
    public List<Map<String, Object>> getFlaggedTransactions() {

        String sql = """
        SELECT card_no, amount, merchant_id, ip_address, flagged, created_at
        FROM fraud_logs
        WHERE flagged = 1
        ORDER BY created_at DESC
    """;

        return jdbcTemplate.queryForList(sql);
    }
}
