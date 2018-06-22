import java.util.ArrayList;
import java.util.HashMap;
import java.math.BigDecimal;

public class Cart implements DetailProvider
{
	private static int count = 0;
	private static String ID;
	private ArrayList<Product> cart;
	private BigDecimal totalAmount;
	private BigDecimal shippingCost;
	private boolean isCartPaid = false;

	public Cart() {
		int i = ++count;
		ID = "CART00" + i;
		cart = new ArrayList<Product>();
	}

	public String getID() {
		return(ID);
	}

	public void addProduct(Product p) {
		cart.add(p);
	}

	public void addProductByID(String id) {
		ProductCollection pc = ProductCollection.createInstance();
		HashMap<String, Product> ap = pc.getAllProducts();
		cart.add(ap.get(id));
	}

	public void removeProductById(String id) {
		for(Product p : cart) {
			if(p.getID().equals(id)) {
				cart.remove(id);
			}
		}
	}

	public void removeProduct(Product p) {
		cart.remove(p);
	}

	public void removeProductByIndex(int i) {
		cart.remove(i);
	}

	public void setShippingCost(String sc) {
		shippingCost = new BigDecimal(sc);
	}

	public BigDecimal getShippingCost() {
		return(shippingCost);
	}

	public int getCartSize() {
		return(cart.size());
	}

	public boolean checkOutCart() {
		return(isCartPaid = true);
	}

	public void emptyCart() {
		cart.clear();
	}

	public ArrayList<Product> getCart() {
		return(cart);
	}

	public BigDecimal getTotalBill() {
		totalAmount = new BigDecimal("0");
		for(Product p : cart) {
			totalAmount = totalAmount.add(p.getVatInclusivePrice());
		}
		totalAmount = totalAmount.add(shippingCost);
		return(totalAmount);
	}

	public Invoice createInvoice(String invoiceId, Client c, String date, String status) {
		Invoice invoice = null;
		if(isCartPaid) {
			invoice = new Invoice(invoiceId, c, date, status);
		}
		return(invoice);
	}

	public String getDetails() {
		StringBuilder sb = new StringBuilder();
		for(Product  p  : cart) {
			sb.append("~" + p.getID());
			sb.append("~" + p.getBrand());
			sb.append("~" + p.getName());
			sb.append("~" + p.getUnitPrice());
			sb.append("~" + p.getDiscount());
			sb.append("~" + p.getVatInclusivePrice());
		}
		return(sb.toString());
	}
}
