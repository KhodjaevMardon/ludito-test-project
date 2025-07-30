-- liquibase formatted sql

-- changeset mardon:base-1
CREATE SEQUENCE IF NOT EXISTS transaction_sequence START WITH 1 INCREMENT BY 1;

-- changeset mardon:base-2
CREATE TABLE transactions
(
    id          BIGINT       NOT NULL,
    from_user   BIGINT       NOT NULL,
    to_user     BIGINT       NOT NULL,
    amount      BIGINT       NOT NULL,
    timestamp   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    description VARCHAR(255) NOT NULL,
    CONSTRAINT pk_transactions PRIMARY KEY (id)
);

-- changeset mardon:base-3
CREATE TABLE users
(
    id   BIGINT       NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

-- changeset mardon:base-4
ALTER TABLE transactions
    ADD CONSTRAINT FK_TRANSACTIONS_ON_FROM_USER FOREIGN KEY (from_user) REFERENCES users (id);

-- changeset mardon:base-5
ALTER TABLE transactions
    ADD CONSTRAINT FK_TRANSACTIONS_ON_TO_USER FOREIGN KEY (to_user) REFERENCES users (id);

