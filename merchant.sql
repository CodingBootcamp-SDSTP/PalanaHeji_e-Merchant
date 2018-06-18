DROP TABLE IF EXISTS clients;
CREATE TABLE clients (
	clientid INTEGER NOT NULL AUTO_INCREMENT,
	firstname varchar(225) NOT NULL,
	lastname varchar(225) NOT NULL,
	contact varchar(225) NOT NULL,
	city varchar(225) NOT NULL,
	country varchar(225) NOT NULL,
	PRIMARY KEY(clientid)
);
INSERT INTO clients (firstname, lastname, contact, city, country) VALUES
('heji', 'palanski', 'hejipalanski@gmail.com', 'quezon city', 'ph');

DROP TABLE IF EXISTS products;
CREATE TABLE products (
	productid INTEGER NOT NULL AUTO_INCREMENT,
	name varchar(255) NOT NULL,
	price INTEGER,
	size varchar(255),
	material varchar(255),
	PRIMARY KEY(productid)
);
INSERT INTO products (name, description) VALUES
('Tshirt', 500, 'XL', 'cotton');


DROP TABLE IF EXISTS orderlist;
CREATE TABLE orderlist (
	orderlistid INTEGER NOT NULL AUTO_INCREMENT,
	clientid INTEGER,
	PRIMARY KEY(orderlistid),
	FOREIGN KEY (clientid) REFERENCES clients (clientid)
);

DROP TABLE IF EXISTS orderitems;
CREATE TABLE orderitems (
	id INTEGER NOT NULL AUTO_INCREMENT,
	orderlistid INTEGER,
	productid INTEGER,
	quantity INTEGER,
	PRIMARY KEY(id),
	FOREIGN KEY (productid) REFERENCES products (productid),
	FOREIGN KEY (orderlistid) REFERENCES orderlist (orderlistid)

);
INSERT INTO orderitems (quantity) VALUES (4);