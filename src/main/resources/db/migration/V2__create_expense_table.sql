
CREATE TABLE expense (
    id BIGSERIAL NOT NULL,
    customer_id BIGSERIAL NOT NULL,
    description VARCHAR(255),
    value DOUBLE PRECISION,
    date TIMESTAMP,

    PRIMARY KEY (id),
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customer(id)
);
