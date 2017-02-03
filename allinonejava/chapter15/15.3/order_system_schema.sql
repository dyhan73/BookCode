use order_system;

CREATE TABLE customer
(
	customer_id		INTEGER NOT NULL AUTO_INCREMENT,
	name                 	VARCHAR(20) NOT NULL,
	address              	VARCHAR(60) NULL,
	email                	VARCHAR(40) NULL,
	PRIMARY KEY (customer_id)
);

CREATE TABLE order_item
(
	order_item_id     	INTEGER NOT NULL AUTO_INCREMENT,
	amount               	INTEGER NOT NULL,
	product_id          	INTEGER NULL,
	order_id             	INTEGER NULL,
	PRIMARY KEY (order_item_id)
);
CREATE TABLE orders
(
	order_id             	INTEGER NOT NULL AUTO_INCREMENT,
	order_date          DATE NOT NULL,
	customer_id        INTEGER NULL,
	PRIMARY KEY (order_id)
);

CREATE TABLE product
(
	product_id          INTEGER NOT NULL AUTO_INCREMENT,
	name                 	VARCHAR(20) NOT NULL,
	price                	INTEGER NOT NULL,
	description          VARCHAR(60) NULL,
	PRIMARY KEY (product_id)
);

ALTER TABLE order_item
ADD FOREIGN KEY R_5 (product_id) REFERENCES product (product_id);
ALTER TABLE order_item
ADD FOREIGN KEY R_7 (order_id) REFERENCES orders (order_id);
ALTER TABLE orders
ADD FOREIGN KEY R_6 (customer_id) REFERENCES customer (customer_id);