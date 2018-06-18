public class Person
{
	private String firstName;
	private String lastName;
	private int age;

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

	public void setAge(int a) {
		age = a;
	}

	public int getAge() {
		return(age);
	}

	public String getDetails() {
		return(firstName + "~" + lastName + "~" + age);
	}
}
