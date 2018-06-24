import java.util.ArrayList;
import java.util.HashMap;
import java.sql.*;

public class StoreManager
{
	private static StoreManager _instance;
	private ProductCollection productCollection = null;
	private ClientCollection clientCollection = null;
	private InvoiceCollection invoiceCollection = null;
	private static Connection conn;

	private StoreManager() {
		productCollection = ProductCollection.createInstance();
		clientCollection = ClientCollection.createInstance();
		invoiceCollection = InvoiceCollection.createInstance();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/eshopdb?user=eshopdb&password=eshopdb" +
					"&serverTimezone=UTC&useSSL=false");
			readFromDB(conn);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static StoreManager createInstance() {
		if(_instance == null) {
			_instance = new StoreManager();
		}
		return(_instance);
	}

	public ProductCollection getProductCollection() {
		return(productCollection);
	}

	public ClientCollection getClientCollection() {
		return(clientCollection);
	}

	public InvoiceCollection getInvoiceCollection() {
		return(invoiceCollection);
	}

	public Product getProductByID(String id) {
		return(productCollection.getProductByID(id));
	}

	public Person getClientByID(String id) {
		return(clientCollection.getClientByID(id));
	}

	public Invoice getInvoiceByID(String id) {
		return(invoiceCollection.getInvoiceByID(id));
	}

	public boolean readFromDB(Connection conn) {
		System.out.println("StoreManager connected to DB");
		Statement stmt = null;
		ResultSet rs = null;
		ResultSet rsForInv = null;
		ResultSet persondetails = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM clients;");
			while(rs.next()) {
				String[] details = {
					rs.getString("clientid"),
					rs.getString("firstname"),
					rs.getString("lastname"),
					rs.getString("birthdate"),
					rs.getString("email"),
					rs.getString("password"),
					rs.getString("companyname"),
					rs.getString("city"),
					rs.getString("state"),
					rs.getString("zipcode"),
					rs.getString("country"),
					rs.getString("address"),
					rs.getString("contact"),
					"client"
				};
				createObject(details);
			}
			rs = stmt.executeQuery("SELECT * FROM products;");
			while(rs.next()) {
				String[] details = {
					rs.getString("productid"),
					rs.getString("productname"),
					rs.getString("brand"),
					rs.getString("unitPrice"),
					rs.getString("discount"),
					rs.getString("vat"),
					rs.getString("numberOfSales"),
					"product"
				};
				createObject(details);
			}
			String cartDetails = "SELECT carts.cartid, products.productid, productname," +
			"brand, discount, quantity, vatInclusivePrice FROM products INNER JOIN " +
			"orders ON orders.productid=products.id INNER JOIN carts ON orders.cartid=carts.id ORDER BY cartid;";
			rsForInv = stmt.executeQuery(cartDetails);
			ArrayList<ArrayList<String>> orderDetails = new ArrayList<ArrayList<String>>();
			while(rsForInv.next()) {
				ArrayList<String> details = new ArrayList<String>();
					details.add(rsForInv.getString("cartid"));
					details.add(rsForInv.getString("productid"));
					details.add(rsForInv.getString("productname"));
					details.add(rsForInv.getString("brand"));
					details.add(rsForInv.getString("quantity"));
					details.add(rsForInv.getString("discount"));
					details.add(rsForInv.getString("vatInclusivePrice"));
				orderDetails.add(details);
				details = null;
			}
			String personDetailsq = "SELECT DISTINCT invoices.invoiceid, carts.cartid," +
			"firstname, lastname, email, contact FROM clients INNER JOIN orders ON " +
			"orders.clientid=clients.id INNER JOIN carts ON orders.cartid=carts.id " +
			"INNER JOIN invoices ON orders.cartid=invoices.cartid";
			persondetails = stmt.executeQuery(personDetailsq);
			ArrayList<ArrayList<String>> clientDetails = new ArrayList<ArrayList<String>>();
			while(persondetails.next()) {
				ArrayList<String> details = new ArrayList<String>();
					details.add(persondetails.getString("invoiceid"));
					details.add(persondetails.getString("cartid"));
					details.add(persondetails.getString("firstname"));
					details.add(persondetails.getString("lastname"));
					details.add(persondetails.getString("email"));
					details.add(persondetails.getString("contact"));
				clientDetails.add(details);
				details = null;
			}
			ArrayList<String> detailsForInvoice = null;
			HashMap<String, String> invoice = new HashMap<String, String>();
			String cartid;
			String orderToString;
			String clientToString;
			for(ArrayList<String> order : orderDetails) {
				for(ArrayList<String> client : clientDetails) {
					cartid = order.get(0);
					if(cartid.equals(client.get(1))) {
						order.remove(cartid);
						orderToString = order.toString();
						if(!invoice.containsKey(cartid)) {
							invoice.put(cartid, orderToString);
						}
						else {
							clientToString = client.toString();
							invoice.put(cartid, invoice.get(cartid).concat(orderToString));
							if(!invoice.get(cartid).contains(clientToString)) {
								 invoice.put( cartid, invoice.get(cartid).concat(clientToString));
							}
							detailsForInvoice = new ArrayList<String>(invoice.values());
						}
					}
				}
			}
			String invoiceDetailsq = "SELECT invoiceid, carts.cartid, clients.clientid, transactionDate," +
			"dateOfPayment, destinationAddress, shippingCost, totalBill, status FROM " +
			"clients INNER JOIN orders ON orders.clientid=clients.id INNER JOIN " +
			"carts ON orders.cartid=carts.id INNER JOIN invoices ON orders.cartid=invoices.cartid;";
			rs = stmt.executeQuery(invoiceDetailsq);
			String cartd = "";
			while(rs.next()) {
				for(String d : detailsForInvoice) {
					if(d.contains(rs.getString("cartid"))) {
						cartd = d;
					}
				}
				String[] details = {
					rs.getString("invoiceid"),
					rs.getString("clientid"),
					rs.getString("cartid"),
					rs.getString("transactionDate"),
					rs.getString("dateOfPayment"),
					rs.getString("destinationAddress"),
					cartd,
					rs.getString("shippingCost"),
					rs.getString("totalBill"),
					rs.getString("status"),
					"invoice"
				};
				createObject(details);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return(false);
		}
		finally {
			try { if(stmt != null) { stmt = null; } } catch(Exception e) { e.printStackTrace(); }
			try { if(rs != null) { rs = null; } } catch(Exception e) { e.printStackTrace(); }
			try { if(rsForInv != null) { rsForInv = null; } } catch(Exception e) { e.printStackTrace(); }
			try { if(persondetails != null) { persondetails = null; } } catch(Exception e) { e.printStackTrace(); }
		}
		return(true);
	}

	public void createObject(String[] details) {
		int len = details.length-1;
		String d = details[len];
		switch(d) {
			case "client":
				Client c = new Client(details[0], details[1], details[2], details[3],
					details[4], details[5], details[6], details[7], details[8],
					details[9], details[10], details[11], details[12]);
				clientCollection.addClient(c);
				break;
			case "product":
				Product p = new Product(details[0], details[2], details[1], details[3],
				details[4], details[5], Integer.parseInt(details[6]));
				productCollection.addProduct(p);
				break;
			case "invoice":
				Invoice i = new Invoice(details[0], details[1], details[2], details[3],
					details[4], details[5], details[6], details[7], details[8],
					details[9]);
				invoiceCollection.addInvoice(i);
				break;
		}
	}

	public boolean addProduct(ArrayList<String> prodDetails) {
		try {
			PreparedStatement stmt = conn.prepareStatement(
				"INSERT INTO products (productid, name, brand, unitPrice, discount," +
				"vat, numberOfSales, vatInclusivePrice) VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
				int i = 0;
				for(String d : prodDetails) {
					stmt.setString(++i, d);
				}
				stmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			return(false);
		}
		return(true);
	}

	public boolean updateProductDetails(ArrayList<String> prodDetails) {
		try {
			PreparedStatement stmt = conn.prepareStatement(
				"UPDATE products set productid = ?, productname = ?, brand = ?," +
				"numberOfSales = ?, unitPrice = ?, discount = ?, vat = ?, vatInclusivePrice = ? where id = ?;");
				int i = 0;
				for(String d : prodDetails) {
					stmt.setString(++i, d);
				}
				stmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			return(false);
		}
		return(true);
	}

	public boolean addClient(ArrayList<String> clientDetails) {
		try {
			PreparedStatement stmt = conn.prepareStatement(
				"INSERT INTO clients (clientid, firstname, lastname, birthdate," +
				"email, password, companyname, city, state, zipcode, country," +
				"address, contact) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
				int i = 0;
				for(String d : clientDetails) {
					stmt.setString(++i, d);
				}
				stmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			return(false);
		}
		return(true);
	}

	public boolean addInvoice(ArrayList<String> invoiceDetails) {
		try {
			PreparedStatement stmt = conn.prepareStatement(
				"INSERT INTO invoices (invoiceid, cartid, transactionDate, dateOfPayment," +
				"destinationAddress, shippingCost, totalBill) VALUES (?, ?, ?, ?, ?, ?, ?);");
				int i = 0;
				for(String d : invoiceDetails) {
					stmt.setString(++i, d);
				}
				stmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			return(false);
		}
		return(true);
	}

	public boolean addOrder(ArrayList<String> orderDetails) {
		try {
			PreparedStatement stmt = conn.prepareStatement(
				"INSERT INTO orders (clientid, productid, cartid, quantity) VALUES (?, ?, ?, ?);");
				int i = 0;
				for(String d : orderDetails) {
					stmt.setString(++i, d);
				}
				stmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			return(false);
		}
		return(true);
	}

	public boolean updateOrder(ArrayList<String> orderDetails) {
		try {
			PreparedStatement stmt = conn.prepareStatement(
				"UPDATE orders set productid = ?, quantity = ? WHERE id = ?;");
				int i = 0;
				for(String d : orderDetails) {
					stmt.setString(++i, d);
				}
				stmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			return(false);
		}
		return(true);
	}

	public static boolean addCart(String cartID) {
		try {
			PreparedStatement stmt = conn.prepareStatement(
				"INSERT INTO carts (cartid) VALUES (?);");
				stmt.setString(1, cartID);
				stmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			return(false);
		}
		return(true);
	}

	public ArrayList<Object> search(String s) {
		ArrayList<Object> ao = new ArrayList<Object>();
		ArrayList<Person> cs = clientCollection.searchClient(s);
		ArrayList<Product> ps = productCollection.searchProduct(s);
		ArrayList<Invoice> is = invoiceCollection.searchInvoice(s);
		ao.addAll(cs);
		ao.addAll(ps);
		ao.addAll(is);
		return(ao);
	}
}
