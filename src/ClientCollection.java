import java.util.HashMap;
import java.util.ArrayList;

public class ClientCollection
{
	private static ClientCollection _cc = null;
	private HashMap<String, Person> clients;
	private ArrayList<Person> arrayListOfClients = null;

	private ClientCollection() {
		clients = new HashMap<String, Person>();
	}

	public static ClientCollection createInstance() {
		if(_cc == null) {
			_cc = new ClientCollection();
		}
		return(_cc);
	}

	public void  addClient(Client p) {
		clients.putIfAbsent(p.getID(), p);
	}

	public Person getClientByID(String id) {
		return(clients.get(id));
	}

	public void removeClient(Person p) {
		clients.remove(p);
	}

	public int getNumberOfClients() {
		return(clients.size());
	}

	public HashMap<String, Person> getAllClients() {
		return(clients);
	}

	public ArrayList<Person> getArrayListOfClients() {
		if(arrayListOfClients == null) {
			arrayListOfClients = new ArrayList<Person>(clients.values());
		}
		return(arrayListOfClients);
	}

	public ArrayList<Person> searchClient(String q) {
		ArrayList<Person> ap = new ArrayList<Person>();
		for(Person c : getArrayListOfClients() ) {
			if(matches(c, q)) {
				ap.add(c);
			}
		}
		return(ap);
	}

	public boolean matches(Person p, String q) {
		String d = p.getDetails().toLowerCase();
		String query = q.toLowerCase();
		boolean res = d.contains(query) ? true : false;
		return(res);
	}
}
