package application;

public class CustomerData {

	private int ID;
	private String City;

	public CustomerData(int iD, String city) {
		this.ID = iD;
		this.City = city;
	}

	public CustomerData(String city) {
		this.City = city;
	}
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

}
