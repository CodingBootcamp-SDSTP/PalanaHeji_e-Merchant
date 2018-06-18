import java.math.BigDecimal;
import java.util.Date;

public class Invoice
{
	private static int count =0;
	private final String ID;
	private String customerID;
	private final String transcationDate;
	private BigDecimal totalBill;
	private String destinationCity;
	private String destinationState;
	private String destinationZipcode;
	private String destinationCountry;
	private String destinationAddress;
	private Date transactionDate;
	private String cartDetails;
	private Client client = null;
	private String dateOfPayment = null;

	public Invoice(String clientId, String total, String address, String zipcode,
	String city, String country, String state, String cartDetails) {
		customerID = clientId;
		totalBill = new BigDecimal(total);
		destinationAddress = address;
		destinationZipcode = zipcode;
		destinationCity = city;
		destinationCountry = country;
		destinationState = state;
		this.cartDetails = cartDetails;
		ID = "PifabsInvoice#" + ++count;
		transcationDate = new Date().toString();
		dateOfPayment = null;
	}

	public Invoice(Client c) {
		ID = "PifabsInvoice#" + ++count;
		customerID = c.getID();
		totalBill = c.getCart().getTotalBill();
		destinationAddress = c.getAddress();
		destinationZipcode = c.getZipcode();
		destinationCity = c.getCity();
		destinationCountry = c.getCountry();
		transcationDate = new Date().toString();
		dateOfPayment = null;
	}

	public String getInvoiceID() {
		return(ID);
	}

	public String getCustomerID() {
		return(customerID);
	}

	public void seTotalBill(String b) {
		totalBill = new BigDecimal(b);
	}

	public BigDecimal getTotalBill() {
		return(totalBill);
	}

	public void setDestinationAddress(String a) {
		destinationAddress = a;
	}

	public String getDestinationAddress() {
		return(destinationAddress);
	}

	public void setDestinationCity(String c) {
		destinationCity = c;
	}

	public String getDestinationCity() {
		return(destinationCity);
	}

	public void setDestinationState(String s) {
		destinationState = s;
	}
	
	public String getDestinationState() {
		return(destinationState);
	}

	public void setDestinationZipcode(String d) {
		destinationZipcode = d;
	}

	public String getDestinationZipcode() {
		return(destinationZipcode);
	}

	public void setDestinationCountry(String c) {
		destinationCountry = country;
	}

	public String getDestinationCountry() {
		return(destinationCountry);
	}

	public void setCartDetails(String dt) {
		cartDetails = dt;
	}

	public String getCartDetails() {
		return(client.getCart().getDetails());
	}
}
