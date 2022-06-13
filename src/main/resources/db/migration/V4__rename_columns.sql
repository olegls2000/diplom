ALTER TABLE customer RENAME COLUMN firstName TO first_name;
ALTER TABLE customer RENAME COLUMN lastName TO last_name;
ALTER TABLE customer RENAME COLUMN registrationCode TO registration_code;

ALTER TABLE product RENAME COLUMN skuCode TO sku_code;
ALTER TABLE product RENAME COLUMN unitPrice TO unit_price;