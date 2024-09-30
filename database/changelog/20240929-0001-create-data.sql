--liquibase formatted sql

--changeset felipe.aguiar:1
--rollback DELETE FROM client WHERE email IN ('alice@example.com', 'bob@example.com', 'charlie@example.com');
INSERT INTO client (id, email, name) VALUES
(1, 'alice@example.com', 'Alice Wonderland'),
(2, 'bob@example.com', 'Bob Builder'),
(3, 'charlie@example.com', 'Charlie Chaplin');

SELECT setval('client_id_seq', (SELECT MAX(id) FROM client));

--changeset felipe.aguiar:2
--rollback DELETE FROM product WHERE name IN ('Widget', 'Gadget', 'Thingamajig');
INSERT INTO product (id, name, price) VALUES
(1, 'Widget', 9.99),
(2, 'Gadget', 19.99),
(3, 'Thingamajig', 14.99);

SELECT setval('product_id_seq', (SELECT MAX(id) FROM product));