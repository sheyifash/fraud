CREATE TABLE dbo.blacklisted_merchants (
                                           merchant_id      VARCHAR(50) PRIMARY KEY,
                                           merchant_name    VARCHAR(100),
                                           suspicion_count  INT DEFAULT 0,
                                           black_listed_at  DATETIME DEFAULT GETDATE()
);