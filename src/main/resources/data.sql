-- Pre-seed operation types
-- IDs 1-3: Negative transactions (PURCHASE, INSTALLMENT PURCHASE, WITHDRAWAL)
-- ID 4: Positive transaction (PAYMENT)

INSERT INTO operation_types (operation_type_id, description) VALUES
(1, 'PURCHASE'),
(2, 'INSTALLMENT PURCHASE'),
(3, 'WITHDRAWAL'),
(4, 'PAYMENT')
ON CONFLICT (operation_type_id) DO NOTHING;
