CREATE TABLE BlacklistedMerchants (
                                      merchantId VARCHAR(50) PRIMARY KEY,
                                      merchantName VARCHAR(50),
                                      suspicionCount INT DEFAULT 0,
                                      blackListedAt DATETIME DEFAULT GETDATE()
);

CREATE TABLE TransactionModel (
                                  transId VARCHAR(255) PRIMARY KEY,
                                  createdAt DATETIME DEFAULT GETDATE(),
                                  amount DECIMAL(15, 2) NOT NULL,
                                  merchantId VARCHAR(50),
                                  tokenizedCardNo VARCHAR(255),
                                  merchantName VARCHAR(255),
                                  maskedIpAddress VARCHAR(255),
                                  transactionStatus VARCHAR(20) NOT NULL,

                                  CONSTRAINT fk_merchant
                                      FOREIGN KEY (merchantId)
                                          REFERENCES BlacklistedMerchants(merchantId),

                                  CONSTRAINT chk_transaction_status
                                      CHECK (transactionStatus IN ('SUCCESS', 'FAILED', 'FLAGGED'))
);