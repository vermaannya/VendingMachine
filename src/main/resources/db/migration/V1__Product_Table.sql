CREATE TABLE IF NOT EXISTS products
(
    product_id          SERIAL PRIMARY KEY,
    name        CHARACTER VARYING UNIQUE NOT NULL,
    description CHARACTER VARYING,
    price       NUMERIC(10, 2)           NOT NULL
);

CREATE TABLE IF NOT EXISTS orders
(
    id             SERIAL PRIMARY KEY,
    product_id     INTEGER        NOT NULL,
    order_amount   NUMERIC(15, 2) NOT NULL,
    order_quantity INTEGER        NOT NULL,
    constraint orders_product_id_fk foreign key (product_id) references products (product_id) on delete cascade
);

CREATE TABLE IF NOT EXISTS inventory
(
    id         SERIAL PRIMARY KEY,
    product_id INTEGER NOT NULL,
    stock      INTEGER NOT NULL,
    constraint inventory_product_id_fk foreign key (product_id) references products (product_id) on delete cascade
);