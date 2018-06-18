public class StoreManager
{
	private static StoreManager _sm;
	private ProductCollection pc;
	private ClientCollection cc;

	private StoreManager() {
		pc = ProductCollection.createInstance();
		cc = ClientCollection.createInstance();
	}

	public StoreManager instance() {
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
}
