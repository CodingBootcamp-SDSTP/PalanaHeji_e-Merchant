import java.math.BigDecimal;
import java.util.Date;

public class Invoice implements DetailProvider
{
	private final String ID;
	private String customerID;
	private String cartId;
	private final String transcationDate;
	private final String dateOfPayment;
	private String destinationAddress;
	private String cartDetails;
	private BigDecimal shippingCost;
	private BigDecimal totalBill;
	private String status;
	private boolean isPaid = false;

	public Invoice(String id, String clientId, String cartId, String transactionDate,
		String datePaid, String cartDetails, String address, String shipCost, String total, String status) {
		ID = id;
		customerID = clientId;
		this.cartId = cartId;
		transcationDate = transactionDate;
		dateOfPayment = datePaid;
		destinationAddress = address;
		this.cartDetails = cartDetails;
		shippingCost = new BigDecimal(shipCost);
		totalBill = new BigDecimal(total);
		this.status = status;
	}

	public Invoice(String id, Client c, String datePaid, String status) {
		ID = id;
		customerID = c.getID();
		cartId = c.getCart().getID();
		transcationDate = new Date().toString();
		dateOfPayment = datePaid;
		destinationAddress = c.getAddress();
		cartDetails = c.getCart().getDetails();
		shippingCost =  c.getCart().getShippingCost();
		totalBill =  c.getCart().getTotalBill();
		this.status = status;
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

	public void setOrderStatus(String status) {
		this.status =status;
	}

	public String getOrderStatus() {
		return(status);
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
			sb.append("~" + cartId);
			sb.append("~" + transcationDate);
			sb.append("~" + dateOfPayment);
			sb.append("~" + destinationAddress);
			sb.append("~" + cartDetails);
			sb.append("~" + shippingCost.toString());
			sb.append("~" + totalBill.toString());
			sb.append("~" + status);
		return(sb.toString());
	}
}
