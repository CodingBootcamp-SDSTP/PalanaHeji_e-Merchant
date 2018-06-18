import java.util.ArrayList;
import java.util.HashMap;

public class LocationCollection
{
	private static LocationCollection _lc;
	private HashMap<String, Location> locations = null;
	private ArrayList<Location> arryListOfLocations = null;

	private LocationCollection() {
		locations = new HashMap<String, Location>();
	}

	public static LocationCollection createInstance() {
		if(_lc == null) {
			_lc = new LocationCollection();
		}
		return(_lc);
	}

	public void addLocation(String id, Location l) {
		locations.putIfAbsent(id, l);
	}

	public void removeLocation(Location l) {
		locations.remove(l);
	}

	public HashMap<String, Location> getAllLocation() {
		return(locations);
	}

	public ArrayList<Location> getArrayListOfLocation() {
		arryListOfLocations = new ArrayList<Location>(locations.values());
		return(arryListOfLocations);
	}

	public int getLocationCount() {
		return(locations.size());
	}

	public ArrayList<Location> searchByZipCode (String z) {
		ArrayList<Location> al = new ArrayList<Location>();
		for(Location c : getArrayListOfLocation()) {
			if(c.getZipcode().equals(z)) {
				al.add(c);
			}
		}
		return(al);
	}

	public ArrayList<Location> searchLocation(String q) {
		ArrayList<Location> al = new ArrayList<Location>();
		for(Location c : getArrayListOfLocation()) {
			if(matches(c, q)) {
				al.add(c);
			}
		}
		return(al);
	}

	public boolean matches(Location l, String q) {
		String d = l.getDetails().toLowerCase();
		String query = q.toLowerCase();
		boolean res = d.contains(query) ? true : false;
		return(res);
	}
}
