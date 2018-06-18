import java.util.ArrayList;
import java.util.Arrays;
import java.math.BigDecimal;

public class Client extends Person
{
	private static int count = 0;
	private final String ID;
	private String email;
	private String password;
	private String companyName;
	private String invoiceID;
	private String city;
	private String state;
	private String zipcode;
	private String country;
	private String address;
	private Cart cart;

	public Client(String fn, String ln, int a) {
		setFirstName(fn);
		setLastName(ln);
		setAge(a);
		email = "";
		companyName = "";
		city = "";
		state = "";
		zipcode =  "";
		country = "";
		address = "";
		invoiceID = "";
		ID = "ClientID#" + ++count;
		cart = new Cart();
	}

	public Client(String firstName, String lastName, int age, String email,
	String password, String companyName, String city, String state,
	String zipcode, String country, String address) {
		setFirstName(firstName);
		setLastName(lastName);
		setAge(age);
		setEmail(email);
		this.password = password;
		this.companyName = companyName;
		this.city = city;
		this.state = state;
		this.zipcode =  zipcode;
		this.country = country;
		this.address = address;
		invoiceID = "";
		ID = "ClientID#" + ++count;
		cart = new Cart();
	}

	public String getID() {
		return(ID);
	}

	public void setEmail(String e) {
		email = e;
	}

	public void setPassword(String p) {
		password = p;
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

	public String getPassword() {
		return(password);
	}

	public String getCompanyName() {
		return(companyName);
	}

	public String getInvoiceID() {
		return(invoiceID);
	}

	public void setCity(String c) {
		city = c;
	}

	public String getCity() {
		return(city);
	}

	public void setState(String s) {
		state = s;
	}

	public String getState() {
		return(state);
	}

	public void setZipcode(String z) {
		zipcode = z;
	}

	public String getZipcode() {
		return(zipcode);
	}

	public void setCountry(String c) {
		country = c;
	}

	public String getCountry() {
		return(country);
	}

	public void setAddress(String a) {
		address = a;
	}

	public String getAddress() {
		return(address);
	}

	public Cart getCart() {
		return(cart);
	}

	@Override
	public String getDetails() {
		StringBuilder sb = new StringBuilder(super.getDetails());
		sb.append("~" + ID);
		sb.append("~" + email);
		sb.append("~" + companyName);
		sb.append("~" + invoiceID);
		sb.append("~" + city);
		sb.append("~" + state);
		sb.append("~" + zipcode);
		sb.append("~" + country);
		sb.append("~" + address);
		sb.append("~" +  cart.getDetails());
		return(sb.toString());
	}
}
