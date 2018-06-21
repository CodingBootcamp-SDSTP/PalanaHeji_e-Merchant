import java.math.BigDecimal;
import java.util.Date;

public class Invoice implements DetailProvider
{
	// private static int count = 0;
	private final String ID;
	private String customerID;
	private final String transcationDate;
	private final String dateOfPayment;
	private BigDecimal totalBill;
	private String destinationCity;
	private String destinationState;
	private String destinationZipcode;
	private String destinationCountry;
	private String destinationAddress;
	private String cartDetails;
	private BigDecimal shippingCost;
	private boolean isPaid = false;

	public Invoice(String id, String clientId, String total, String address, String zipcode,
	String city, String country, String state, String cartDetails, String datePaid, String shipCost) {
		ID = id;
		customerID = clientId;
		totalBill = new BigDecimal(total);
		shippingCost = new BigDecimal(shipCost);
		destinationZipcode = zipcode;
		destinationCity = city;
		destinationCountry = country;
		destinationState = state;
		this.cartDetails = cartDetails;
		transcationDate = new Date().toString();
		dateOfPayment = datePaid;
		destinationAddress = address;
	}

	public Invoice(String id, Client c, String datePaid) {
		ID = id;
		customerID = c.getID();
		totalBill = c.getCart().getTotalBill();
		destinationAddress = c.getAddress();
		destinationZipcode = c.getZipcode();
		destinationCity = c.getCity();
		destinationCountry = c.getCountry();
		transcationDate = new Date().toString();
		dateOfPayment = datePaid;
		cartDetails = c.getCart().getDetails();
		shippingCost =  c.getCart().getShippingCost();
		totalBill =  c.getCart().getTotalBill();
	}

	public String getID() {
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
		destinationCountry = c;
	}

	public String getDestinationCountry() {
		return(destinationCountry);
	}

	public void setCartDetails(String dt) {
		cartDetails = dt;
	}

	public String getCartDetails() {
		return(cartDetails);
	}

	public String getDetails() {
		StringBuilder sb = new StringBuilder(ID);
			sb.append("~" + customerID);
			sb.append("~" + transcationDate);
			sb.append("~" + dateOfPayment);
			sb.append("~" + destinationCity);
			sb.append("~" + destinationState);
			sb.append("~" + destinationZipcode);
			sb.append("~" + destinationCountry);
			sb.append("~" + destinationAddress);
			sb.append("~" + cartDetails);
			sb.append("~" + shippingCost.toString());
			sb.append("~" + totalBill.toString());
		return(sb.toString());
	}
}
