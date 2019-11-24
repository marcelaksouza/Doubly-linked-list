//person class
public class Person {
	//Attributes
	private int id;
	private String firstName;
	private String lastName;
	private String date;
	private String passport;
	private Priority priority;
	
	//constructors
	Person(){}
	
	public Person(String fName, String lName, String passport, Priority priority) {
		this.firstName = fName;
		this.lastName = lName;
		this.passport = passport;
		this.priority = priority;
	}
	
	public Person(int id, String fName, String lName, String passport, Priority priority, String date) {
		this.id = id;
		this.firstName = fName;
		this.lastName = lName;
		this.passport = passport;
		this.priority = priority;
		this.date = date;
	}
	
	//getters and setters
	public int getId() {
		return id;
	}
	
	public int setId(int id) {
		return this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public String setFirstName(String firstName) {
		return this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String setLastName(String lastName) {
		return this.lastName = lastName;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getPassport() {
		return passport;
	}
	
	public String setPassport(String passport) {
		return this.passport = passport;
	}
	
	public Priority getPriority() {
		return priority;
	}
	
	public Priority setPriority(Priority priority) {
		return this.priority = priority;
	}
	
	//@Override to string method to print Person's details
	@Override
	public String toString() {
		return "Priority " +getPriority()+ " id " +getId()+ " "+ getFirstName()+" "+ getLastName()+" Passport "+ getPassport() +" Arrivel date " + getDate();
	}
	
}

