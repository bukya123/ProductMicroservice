CREATE TABLE category
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_at    datetime              NULL,
    updated_at    datetime              NULL,
    category_name VARCHAR(255)          NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE category_products
(
    category_id BIGINT NOT NULL,
    products_id BIGINT NOT NULL
);

CREATE TABLE product
(
    id                  BIGINT AUTO_INCREMENT NOT NULL,
    created_at          datetime              NULL,
    updated_at          datetime              NULL,
    product_name        VARCHAR(255)          NULL,
    product_description VARCHAR(255)          NULL,
    product_price       DOUBLE                NOT NULL,
    category_id         BIGINT                NULL,
    image               VARCHAR(255)          NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

ALTER TABLE category_products
    ADD CONSTRAINT uc_category_products_products UNIQUE (products_id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);

ALTER TABLE category_products
    ADD CONSTRAINT fk_catpro_on_category FOREIGN KEY (category_id) REFERENCES category (id);

ALTER TABLE category_products
    ADD CONSTRAINT fk_catpro_on_product FOREIGN KEY (products_id) REFERENCES product (id);