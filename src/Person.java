public class Person implements DetailProvider
{
	private String firstName;
	private String lastName;
	private String birthDate;

	public void setFirstName(String fn) {
		firstName = fn;
	}

	public String getFirstName() {
		return(firstName);
	}

	public void setLastName(String ln) {
		lastName = ln;
	}

	public String getLastName() {
		return(lastName);
	}

	public void setbirthDate(String a) {
		birthDate = a;
	}

	public int birthDate() {
		return(birthDate);
	}

	public String getDetails() {
		return(firstName + "~" + lastName + "~" + age);
	}
}
