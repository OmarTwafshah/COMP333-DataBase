package application;

public class ChildrenData {

	private int Emp_ID;
	private String Name;
	private int SerialNumber;
	private String DateBirth;
	private String Gender;

	public ChildrenData(int emp_ID, String name, int serialNumber, String dateBirth, String gender) {
		this.Emp_ID = emp_ID;
		this.Name = name;
		this.SerialNumber = serialNumber;
		this.DateBirth = dateBirth;
		this.Gender = gender;
	}

	public int getEmp_ID() {
		return Emp_ID;
	}

	public void setEmp_ID(int emp_ID) {
		Emp_ID = emp_ID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getSerialNumber() {
		return SerialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		SerialNumber = serialNumber;
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

}
