--liquibase formatted sql

--changeset felipe.aguiar:1
--rollback DROP TABLE client;
CREATE TABLE client (
	id bigserial,
	email varchar(255) NOT NULL,
	name varchar(255) NOT NULL,
	CONSTRAINT pk_client PRIMARY KEY (id)
);

--changeset felipe.aguiar:2
--rollback DROP TABLE item;
CREATE TABLE item (
	id bigserial,
	quantity integer NOT NULL,
	unit_price numeric(38,2) NOT NULL,
	id_order bigint,
	id_product bigint NOT NULL,
	CONSTRAINT pk_item PRIMARY KEY (id)
);

--changeset felipe.aguiar:3
--rollback DROP TABLE order_data;
CREATE TABLE order_data (
	id bigserial,
	status smallint NOT NULL check (status between 0 and 2),
	created_at timestamp(6) NOT NULL,
	updated_at timestamp(6),
	id_client bigint NOT NULL,
	CONSTRAINT pk_order PRIMARY KEY (id)
);

--changeset felipe.aguiar:4
--rollback DROP TABLE product;
CREATE TABLE product (
	id bigserial,
	name varchar(255) NOT NULL,
	price numeric(38,2) NOT NULL,
	CONSTRAINT pk_product PRIMARY KEY (id)
);

--changeset felipe.aguiar:5
--rollback ALTER TABLE item DROP CONSTRAINT fk_item_order;
ALTER TABLE IF EXISTS item
	ADD CONSTRAINT fk_item_order
	FOREIGN KEY (id_order)
	REFERENCES order_data;

--changeset felipe.aguiar:6
--rollback DROP INDEX idx_item_id_order;
CREATE INDEX idx_item_id_order ON item (id_order);

--changeset felipe.aguiar:7
--rollback ALTER TABLE item DROP CONSTRAINT fk_item_product;
ALTER TABLE IF EXISTS item
	ADD CONSTRAINT fk_item_product
	FOREIGN KEY (id_product)
	REFERENCES product;

--changeset felipe.aguiar:8
--rollback DROP INDEX idx_item_id_product;
CREATE INDEX idx_item_id_product ON item (id_product);

--changeset felipe.aguiar:9
--rollback ALTER TABLE order DROP CONSTRAINT fk_order_client;
ALTER TABLE IF EXISTS order_data
	ADD CONSTRAINT fk_order_client
	FOREIGN KEY (id_client)
	REFERENCES client;

--changeset felipe.aguiar:10
--rollback DROP INDEX idx_order_id_client;
CREATE INDEX idx_order_id_client ON order_data (id_client);
