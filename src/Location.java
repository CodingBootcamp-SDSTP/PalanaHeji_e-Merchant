public class Location
{
	private String ID;
	private String name;
	private String city;
	private String state;
	private String zipcode;
	private String country;
	private String address;

	public Location(String id, String n, String c, String s, String z, String co, String a) {
		ID = id;
		name = n;
		city = c;
		state = s;
		zipcode = z;
		country = co;
		address = a;
	}

	public void setID(String id) {
		ID = id;
	}

	public String getID() {
		return(ID);
	}

	public void setName(String n) {
		name = n;
	}

	public String getName() {
		return(name);
	}

	public void setCity(String c) {
		city = c;
	}

	public String getCity() {
		return(city);
	}

	public void setState(String s) {
		state = s;
	}

	public String getState() {
		return(state);
	}

	public void setZipcode(String z) {
		zipcode = z;
	}

	public String getZipcode() {
		return(zipcode);
	}

	public void setCountry(String c) {
		country = c;
	}

	public String getCountry() {
		return(country);
	}

	public void setAddress(String a) {
		address = a;
	}

	public String getAddress() {
		return(address);
	}

	public String getDetails() {
		StringBuilder sb = new StringBuilder(ID);
		sb.append("~" + name);
		sb.append("~" + city);
		sb.append("~" + state);
		sb.append("~" + zipcode);
		sb.append("~" + country);
		sb.append("~" + address);
		return(sb.toString());
	}
}
