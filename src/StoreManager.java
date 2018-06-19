import java.util.ArrayList;

public class StoreManager
{
	private static StoreManager _instance;
	private ProductCollection productCollection = null;
	private ClientCollection clientCollection = null;
	private InvoiceCollection ic = null;

	private StoreManager() {
		productCollection = ProductCollection.createInstance();
		clientCollection = ClientCollection.createInstance();
		ic = InvoiceCollection.createInstance();
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
		return(ic);
	}

	public Product getProductByID(String id) {
		return(productCollection.getProductByID(id));
	}

	public Person getClientByID(String id) {
		return(clientCollection.getClientByID(id));
	}

	public Invoice getInvoiceByID(String id) {
		return(ic.getInvoiceByID(id));
	}

	public ArrayList<Object> search(String s) {
		ArrayList<Object> ao = new ArrayList<Object>();
		ArrayList<Person> cs = clientCollection.searchClient(s);
		ArrayList<Product> ps = productCollection.searchProduct(s);
		ArrayList<Invoice> is = ic.searchInvoice(s);
		ao.addAll(cs);
		ao.addAll(ps);
		ao.addAll(is);
		return(ao);
	}
}
