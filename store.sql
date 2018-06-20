DROP TABLE IF EXISTS invoices;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS carts;
DROP TABLE IF EXISTS clients;
DROP TABLE IF EXISTS products;

CREATE TABLE clients (
	id INTEGER NOT NULL AUTO_INCREMENT,
	clientid varchar(50) NOT NULL,
	firstname varchar(25) NOT NULL,
	lastname varchar(25) NOT NULL,
	companyname varchar(50),
	email varchar(25),
	password varchar(25),
	contact varchar(50),
	state varchar(50),
	zipcode varchar(25),
	address varchar(255),
	city varchar(20),
	country varchar(30),
	PRIMARY KEY(id)
)ENGINE INNODB;

CREATE TABLE products (
	id INTEGER NOT NULL AUTO_INCREMENT,
	productid varchar(50) NOT NULL,
	name varchar(30) NOT NULL,
	brand varchar(50),
	numberOfSales INTEGER,
	unitPrice varchar(20),
	discount varchar(20),
	vat varchar(20),
	vatInclusivePrice varchar(20),
	PRIMARY KEY(id)
)ENGINE INNODB;

CREATE TABLE carts (
	id INTEGER NOT NULL AUTO_INCREMENT,
	cartid varchar(50) NOT NULL,
	PRIMARY KEY(id)
)ENGINE INNODB;

CREATE TABLE orders (
	id INTEGER NOT NULL AUTO_INCREMENT,
	clientid INTEGER,
	productid INTEGER,
	cartid INTEGER,
	quantity INTEGER,
	FOREIGN KEY (clientid) REFERENCES clients (id),
	FOREIGN KEY (productid) REFERENCES products (id),
	FOREIGN KEY (cartid) REFERENCES carts (id),
	PRIMARY KEY(id)
)ENGINE INNODB;

CREATE TABLE invoices (
	id INTEGER NOT NULL AUTO_INCREMENT,
	invoiceid varchar(30),
	cartid INTEGER,
	transactionDate varchar(30),
	dateOfPayment varchar(30),
	destinationCity varchar(30),
	destinationState varchar(30),
	destinationZipcode varchar(30),
	destinationCountry varchar(30),
	destinationAddress varchar(30),
	shippingCost varchar(20),
	totalBill varchar(20),
	status varchar(30),
	FOREIGN KEY (cartid) REFERENCES carts (id),
	PRIMARY KEY(id)
)ENGINE INNODB;

INSERT INTO clients (clientid, firstname, lastname, companyname, email, password, contact, state, zipcode, address, city, country) VALUES
('CLIENT#0001', 'heji', 'palanski', 'Pifabs', 'hejipalanski@gmail.com', 'hsshdfjsd122324', '98980324325', 'sample', '4117', '#455 macopa st. poblacion2 , General Mariano Alvarez, Cavite', 'cavite', 'ph'),
('CLIENT#0002', 'rochelle', 'cubos', 'Pifabs', 'rochelle@gmail.com', '2412jh1214', '234234234235', 'sample', '4121', '#4673 brgy. matungao, Bulacan, Bulacan', 'Bulacan', 'ph'),
('CLIENT#0003', 'darwin', 'flores', 'Pifabs', 'darwin@gmail.com', 'uqwiqw1221', '0982098778', 'sample', '4223', 'A1 sitio veterans, barangay bagong pagasa', 'Quezon city', 'ph');

INSERT INTO products (productid, name, brand, numberOfSales, unitPrice, discount, vat, vatInclusivePrice) VALUES
('PRODUCT#001', 'white t-shirt', 'JAG', 5, '500', '10', '12', '650'),
('PRODUCT#002', 'cargo pants', 'DICKIES', 7, '800', '10', '12', '820'),
('PRODUCT#003', "men's watch", 'FOSSIL', 2, '6500', '0', '12', '7300'),
('PRODUCT#004', 'CASUAL SHOES', 'FILA', 6, '2500', '0', '12', '3200');

INSERT INTO carts (cartid) VALUES
('CART#001'),
('CART#002'),
('CART#003'),
('CART#004');

INSERT INTO orders (clientid, productid, cartid, quantity) VALUES
(1, 1, 1, 5),
(2, 1, 2, 4),
(3, 2, 3, 2),
(3, 2, 4, 2);


INSERT INTO invoices (invoiceid, cartid, transactionDate, dateOfPayment, destinationCountry, destinationZipcode, destinationState, destinationCity, destinationAddress, shippingCost, totalBill) VALUES
('INVOICE#001', 1, 'Tue Jun 19 15:02:34 SGT 2018', 'Tue Jun 25 15:02:34 SGT 2018', 'italy', '30221', 'sicily', 'sicily', 'palto sicily, roma italia', '99.0', '3500'),
('INVOICE#002', 2, 'Mon Jun 19 12:23:34 SGT 2018', 'Fri Jul 25 10:32:32 SGT 2018', 'usa', '5342', 'califonia', 'sacramento', 'palto sicily, roma italia', '30.0', '2100'),
('INVOICE#003', 3, 'Thu Aug 12 02:02:34 SGT 2018', 'Thu Aug 25 15:02:34 SGT 2018', 'malaysia', '2314', 'kula lumphur', 'borneo', 'palto sicily, roma italia', '33.00', '3570'),
('INVOICE#004', 4, 'Fri Aug 12 02:02:34 SGT 2018', 'Wed Aug 25 15:02:34 SGT 2018', 'malaysia', '2314', 'kula lumphur', 'borneo', 'palto sicily, roma italia', '33.00', '3570');
