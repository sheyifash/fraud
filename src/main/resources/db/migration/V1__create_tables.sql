CREATE TABLE blacklisted_merchants (
    merchant_id      VARCHAR(50) PRIMARY KEY,
    merchant_name    VARCHAR(100),
    suspicion_count  INT DEFAULT 0,
    black_listed_at  DATETIME DEFAULT GETDATE()
);

CREATE TABLE transaction_model (
    trans_id            VARCHAR(255) PRIMARY KEY,
    created_at          DATETIME DEFAULT GETDATE(),
    amount              DECIMAL(15, 2) NOT NULL,
    merchant_id         VARCHAR(50),
    tokenized_card_no   VARCHAR(255),
    merchant_name       VARCHAR(255),
    masked_ip_address   VARCHAR(255),
    status              VARCHAR(20) NOT NULL,

    CONSTRAINT fk_merchant
        FOREIGN KEY (merchant_id)
            REFERENCES blacklisted_merchants(merchant_id),

    CONSTRAINT chk_transaction_status
        CHECK (status IN ('SUCCESS', 'FAILED', 'FLAGGED'))
);
