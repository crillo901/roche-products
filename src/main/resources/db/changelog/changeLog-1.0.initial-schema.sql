CREATE TABLE products
(
    id                              BIGSERIAL                                 NOT NULL,
    sku                             VARCHAR(255)                              NOT NULL,
    name                            VARCHAR(255)                              NOT NULL,
    price_in_cents                  INTEGER                                    NOT NULL,
    is_deleted                      BOOLEAN DEFAULT FALSE                     NOT NULL,
    created_at                      TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    updated_at                      TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    version                         BIGINT DEFAULT 0                          NOT NULL,
    PRIMARY KEY (id),
    UNIQUE(sku)
);




