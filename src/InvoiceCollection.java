import java.util.HashMap;
import java.util.ArrayList;

public class InvoiceCollection
{
	private static InvoiceCollection _instance;
	private HashMap<String, Invoice> invoices = null;

	public InvoiceCollection() {
		invoices = new HashMap<String, Invoice>();
	}

	public static InvoiceCollection createInstance() {
		if(_instance == null) {
			_instance = new InvoiceCollection();
		}
		return(_instance);
	}

	public void  addInvoice(Invoice i) {
		invoices.putIfAbsent(i.getID(), i);
	}

	public Invoice getInvoiceByID(String id) {
		return(invoices.get(id));
	}

	public void removeInvoice(Product i) {
		invoices.remove(i);
	}

	public void removeInvoiceByID(String id) {
		invoices.remove(id);
	}

	public int  getNumberOfInvoices() {
		return(invoices.size());
	}

	public HashMap<String, Invoice> getAllInvoices() {
		return(invoices);
	}

	public ArrayList<Invoice> getArrayListOfInvoices() {
		return(new ArrayList<Invoice>(invoices.values()));
	}

	public ArrayList<Invoice> searchInvoice(String q) {
		ArrayList<Invoice> ap = new ArrayList<Invoice>();
		for(Invoice c : getArrayListOfInvoices()) {
			if(matches(c, q)) {
				ap.add(c);
			}
		}
		return(ap);
	}

	public boolean matches(Invoice p, String q) {
		String d = p.getDetails().toLowerCase();
		String query = q.toLowerCase();
		boolean res = d.contains(query) ? true : false;
		return(res);
	}
}
