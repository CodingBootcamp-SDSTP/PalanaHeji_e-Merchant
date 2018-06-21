import java.util.ArrayList;
import java.sql.*;

public class StoreManager
{
	private static StoreManager _instance;
	private ProductCollection productCollection = null;
	private ClientCollection clientCollection = null;
	private InvoiceCollection invoiceCollection = null;
	private Connection conn;

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
					rs.getString("name"),
					rs.getString("brand"),
					rs.getString("unitPrice"),
					rs.getString("discount"),
					rs.getString("vat"),
					rs.getString("numberOfSales"),
					"product"
				};
				createObject(details);
			}
			String cartDetails = "SELECT firstname, lastname, email, state," +
			"zipcode, country, address, name , quantity, vatInclusivePrice FROM" +
			"orders,clients, products WHERE orders.clientid=clients.id AND orders.productid=products.id;";
			rsForInv = stmt.executeQuery(cartDetails);
			StringBuilder sb = new StringBuilder();
			while(rs.next()) {
				sb.append(rsForInv.getString("firstname"));
				sb.append(rsForInv.getString("lastname"));
				sb.append(rsForInv.getString("email"));
				sb.append(rsForInv.getString("state"));
				sb.append(rsForInv.getString("zipcode"));
				sb.append(rsForInv.getString("country"));
				sb.append(rsForInv.getString("address"));
				sb.append(rsForInv.getString("name"));
				sb.append(rsForInv.getString("quantity"));
				sb.append(rsForInv.getString("vatInclusivePrice"));
			}
			rs = stmt.executeQuery("SELECT * FROM invoices;");
			while(rs.next()) {
				String[] details = {
					rs.getString("invoiceid"),
					rs.getString("clientid"),
					rs.getString("cartid"),
					rs.getString("transactionDate"),
					rs.getString("dateOfPayment"),
					rs.getString("destinationCity"),
					rs.getString("destinationState"),
					rs.getString(sb.toString()),
					rs.getString("destinationZipcode"),
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
			try {if(stmt != null) { stmt = null; }} catch(Exception e) { e.printStackTrace(); }
			try {if(rs != null) { rs = null; }} catch(Exception e) { e.printStackTrace(); }
		}
		return(true);
	}

	public void createObject(String[] details) {
		System.out.println(details);
		int len = details.length-1;
		String d = details[len];
		switch(d) {
			case "client":
				Client c = new Client(
					details[0],
					details[1],
					details[2],
					details[3],
					details[4],
					details[5],
					details[6],
					details[7],
					details[8],
					details[9],
					details[10],
					details[11],
					details[12]
					);
				clientCollection.addClient(c);
				break;
			case "product":
				Product p = new Product(details[0],
				details[2],
				details[1],
				details[3],
				details[4],
				details[5],
				Integer.parseInt(details[6])
				);
				productCollection.addProduct(p);
				break;
			case "invoice":
				Invoice i = new Invoice(
					details[0],
					details[2],
					details[1],
					details[3],
					details[4],
					details[5],
					details[6],
					details[7],
					details[8],
					details[9],
					details[10]
					);
				invoiceCollection.addInvoice(i);
				break;
		}
	}

	public boolean addProduct(ArrayList<String> prodDetails) {
		try {
			//CHECK FIRST IF PRODUCTID IS ALREADY EXISTS IN DATABASE
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

	public boolean addClient(ArrayList<String> clientDetails) {
		try {
			//CHECK FIRST IF PRODUCTID IS ALREADY EXISTS IN DATABASE
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
