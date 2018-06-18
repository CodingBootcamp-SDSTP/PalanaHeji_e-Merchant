import java.util.ArrayList;

public class StoreManager
{
	private static StoreManager _sm;
	private ProductCollection pc = null;
	private ClientCollection cc = null;

	private StoreManager() {
		pc = ProductCollection.createInstance();
		cc = ClientCollection.createInstance();
	}

	public static StoreManager createInstance() {
		if(_sm == null) {
			_sm = new StoreManager();
		}
		return(_sm);
	}

	public ProductCollection getProductCollection() {
		return(pc);
	}

	public ClientCollection getClientCollection() {
		return(cc);
	}

	public Product getProductByID(String id) {
		return(pc.getProductByID(id));
	}

	public Person getClientByID(String id) {
		return(cc.getClientByID(id));
	}

	public ArrayList<Object> search(String s) {
		ArrayList<Object> ao = new ArrayList<Object>();
		ArrayList<Person> cs = cc.searchClient(s);
		ArrayList<Product> ps = pc.searchProduct(s);
		ao.addAll(cs);
		ao.addAll(ps);
		return(ao);
	}
}
