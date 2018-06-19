import java.util.HashMap;
import java.util.ArrayList;

public class ClientCollection
{
	private static ClientCollection _instance = null;
	private HashMap<String, Person> clients;

	private ClientCollection() {
		clients = new HashMap<String, Person>();
	}

	public static ClientCollection createInstance() {
		if(_instance == null) {
			_instance = new ClientCollection();
		}
		return(_instance);
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
		return(new ArrayList<Person>(clients.values()));
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
