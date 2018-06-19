DROP TABLE IF EXISTS invoices;
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
	name varchar(255) NOT NULL,
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
	clientid INTEGER,
	productid INTEGER,
	quantity INTEGER,
	FOREIGN KEY (clientid) REFERENCES clients (id),
	FOREIGN KEY (productid) REFERENCES products (id),
	PRIMARY KEY(id)
)ENGINE INNODB;

CREATE TABLE invoices (
	id INTEGER NOT NULL AUTO_INCREMENT,
	clientid INTEGER,
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
	FOREIGN KEY (clientid) REFERENCES clients (id),
	PRIMARY KEY(id)
)ENGINE INNODB;
