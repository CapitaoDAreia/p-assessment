-- Create accounts table
CREATE TABLE IF NOT EXISTS accounts (
    account_id BIGSERIAL PRIMARY KEY,
    document_number VARCHAR(255) NOT NULL UNIQUE
);

-- Create operation_types table
CREATE TABLE IF NOT EXISTS operation_types (
    operation_type_id INTEGER PRIMARY KEY,
    description VARCHAR(50) NOT NULL
);

-- Create transactions table
CREATE TABLE IF NOT EXISTS transactions (
    transaction_id BIGSERIAL PRIMARY KEY,
    account_id BIGINT NOT NULL,
    operation_type_id INTEGER NOT NULL,
    amount DECIMAL(19, 2) NOT NULL,
    event_date TIMESTAMP NOT NULL,
    CONSTRAINT fk_account FOREIGN KEY (account_id) REFERENCES accounts(account_id),
    CONSTRAINT fk_operation_type FOREIGN KEY (operation_type_id) REFERENCES operation_types(operation_type_id)
);

-- Create indexes for better query performance
CREATE INDEX IF NOT EXISTS idx_transactions_account_id ON transactions(account_id);
CREATE INDEX IF NOT EXISTS idx_transactions_event_date ON transactions(event_date);
