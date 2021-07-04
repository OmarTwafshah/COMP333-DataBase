package application;

public class EmployeeData {

	private int ID;
	private String Name;
	private String DateBirth;
	private String Gender;
	private long PhoneNumber;
	private String Address;
	private double Salary;
	

	public EmployeeData( String dateBirth, String address, String name, String gender, long phoneNumber, double salary) {
		this.Name = name;
		this.DateBirth = dateBirth;
		this.Gender = gender;
		this.PhoneNumber = phoneNumber;
		this.Address = address;
		this.Salary = salary;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDateBirth() {
		return DateBirth;
	}

	public void setDateBirth(String dateBirth) {
		DateBirth = dateBirth;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public long getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public double getSalary() {
		return Salary;
	}

	public void setSalary(double salary) {
		Salary = salary;
	}

}
