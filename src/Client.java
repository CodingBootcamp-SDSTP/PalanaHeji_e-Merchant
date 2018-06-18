import java.util.ArrayList;
import java.util.Arrays;

public class Client extends Person
{
	private final String ID;
	private String email;
	private String companyName;
	private ArrayList<String> cart;
	private String invoiceID;

	public Client(String fn, String ln, int a) {
		setFirstName(fn);
		setLastName(ln);
		setAge(a);
		email = "";
		companyName = "";
		invoiceID = "";
		ID = "";
		cart = new ArrayList<String>();
	}

	public Client(String firstName, String lastName, int age, String email, String companyName, String ID) {
		setFirstName(firstName);
		setLastName(lastName);
		setAge(age);
		this.email = email;
		this.companyName = companyName;
		invoiceID = "";
		this.ID = ID;
		cart = new ArrayList<String>();
	}


	public String getID() {
		return(ID);
	}

	public void setEmail(String e) {
		email = e;
	}

	public void setCompanyName(String c) {
		companyName = c;
	}

	public void setInvoiceID(String i) {
		invoiceID = i;
	}

	public String getEmail() {
		return(email);
	}

	public String getCompanyName() {
		return(companyName);
	}

	public String getInvoiceID() {
		return(invoiceID);
	}

	public void addOrderItem(String productID) {
		cart.add(productID);
	}

	public void removeOrderItemByProductID(String productID) {
		for(String i : cart) {
			cart.remove(productID);
		}
	}

	public void removeOrderItemByIndex(int i) {
		cart.remove(i);
	}

	public void clearCart() {
		cart = null;
	}

	public ArrayList<String> getCart() {
		return(cart);
	}

	@Override
	public String getDetails() {
		StringBuilder sb = new StringBuilder(super.getDetails());
		sb.append("~" + ID);
		sb.append("~" + email);
		sb.append("~" + companyName);
		sb.append("~" + invoiceID);
		sb.append("~" +  cart);
		return(sb.toString());
	}
}
