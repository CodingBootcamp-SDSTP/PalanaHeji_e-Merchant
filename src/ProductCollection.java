import java.util.HashMap;
import java.util.ArrayList;
import java.math.BigDecimal;

public class ProductCollection
{
	private static ProductCollection _instance;
	private HashMap<String, Product> products = null;

	public ProductCollection() {
		products = new HashMap<String, Product>();
	}

	public static ProductCollection createInstance() {
		if(_instance == null) {
			_instance = new ProductCollection();
		}
		return(_instance);
	}

	public void  addProduct(Product p) {
		products.putIfAbsent(p.getID(), p);
	}

	public Product getProductByID(String id) {
		return(products.get(id));
	}

	public void removeProduct(Product p) {
		products.remove(p);
	}

	public void removeProductByID(String id) {
		products.remove(id);
	}

	public int  getNumberOfProducts() {
		return(products.size());
	}

	public HashMap<String, Product> getAllProducts() {
		return(products);
	}

	public ArrayList<Product> getArrayListOfProducts() {
		return(new ArrayList<Product>(products.values()));
	}

	public ArrayList<Product> searchByPriceRange(double floorPrice, double ceilingPrice) {
		ArrayList<Product> ap = new ArrayList<Product>();
		for(Product c : getArrayListOfProducts()) {
			double p = c.getunitPrice().doubleValue();
			if(p >= floorPrice && p <= ceilingPrice ) {
				ap.add(c);
			}
		}
		return(ap);
	}

	public ArrayList<Product> searchByBrand(String b) {
		ArrayList<Product> ap = new ArrayList<Product>();
		for(Product c : getArrayListOfProducts()) {
			String br = c.getBrand().toLowerCase();
			if(br.equalsIgnoreCase(b)) {
				ap.add(c);
			}
		}
		return(ap);
	}

	public ArrayList<Product> searchProduct(String q) {
		ArrayList<Product> ap = new ArrayList<Product>();
		for(Product c : getArrayListOfProducts()) {
			if(matches(c, q)) {
				ap.add(c);
			}
		}
		return(ap);
	}

	public boolean matches(Product p, String q) {
		String d = p.getDetails().toLowerCase();
		String query = q.toLowerCase();
		boolean res = d.contains(query) ? true : false;
		return(res);
	}
}
