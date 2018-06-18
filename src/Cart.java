import java.util.ArrayList;
import java.util.HashMap;
import java.math.BigDecimal;

public class Cart
{
	private static String ID;
	private int count = 0;
	private ArrayList<Product> cart;
	private int cartSize;
	private String  details;
	private BigDecimal totalAmount = new BigDecimal("0");
	private ArrayList<String> itemNames;
	private String clientID;
	private boolean isPaid = false;

	public Cart() {
		ID = "Cart#" + count++;
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

	public int getCartSize() {
		return(cart.size());
	}

	public void isPaid(boolean t) {
		isPaid = t;
	}

	public void emptyCart() {
		if(isPaid) {
			cart.clear();
		}
	}

	public ArrayList<Product> getCart() {
		return(cart);
	}

	public BigDecimal getTotalBill() {
		BigDecimal total = new BigDecimal("0");
		for(Product p : cart) {
			total = total.add(p.getDiscountedPrice());
		}
		return(total);
	}

	public String getDetails() {
		StringBuilder sb = new StringBuilder();
		for(Product  p  : cart) {
			sb.append("~" + p.getID());
			sb.append("~" + p.getBrand());
			sb.append("~" + p.getName());
			sb.append("~" + p.getunitPrice());
			sb.append("~" + p.getDiscount());
			sb.append("~" + getTotalBill());
		}
		return(sb.toString());
	}
}
