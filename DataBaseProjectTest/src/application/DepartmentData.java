package application;

public class DepartmentData {

	private int ID;
	private String Name;

	public DepartmentData(int iD, String name) {
		this.ID = iD;
		this.Name = name;
	}
	public DepartmentData(String name) {
		this.Name = name;
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

}
