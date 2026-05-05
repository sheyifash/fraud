CREATE TABLE fraud_logs (
                            id INT IDENTITY(1,1) PRIMARY KEY,
                            card_no VARCHAR(20),
                            amount DECIMAL(15, 2),
                            merchant_id VARCHAR(50),
                            ip_address VARCHAR(50),
                            flagged BIT,
                            created_at DATETIME DEFAULT GETDATE()
);