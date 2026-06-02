CREATE INDEX idx_txn_trans_id ON transaction_model(trans_id);
CREATE INDEX idx_fraud_logs_merchant_id ON fraud_logs(merchant_id);
CREATE INDEX idx_user_merchant_name ON user_model(merchant_name);