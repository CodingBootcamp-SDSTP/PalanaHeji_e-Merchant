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
	birthdate varchar(25) NOT NULL,
	email varchar(25),
	password varchar(25),
	companyname varchar(50),
	city varchar(20),
	state varchar(50),
	zipcode varchar(25),
	country varchar(30),
	address varchar(255),
	contact varchar(50),
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
	destinationAddress varchar(100),
	shippingCost varchar(20),
	totalBill varchar(20),
	status varchar(30),
	FOREIGN KEY (cartid) REFERENCES carts (id),
	PRIMARY KEY(id)
)ENGINE INNODB;
