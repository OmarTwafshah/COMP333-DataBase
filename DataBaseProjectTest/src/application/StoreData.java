package application;

public class StoreData {
	private int ID;
	private String Location;
	private double salesMonth;

	public StoreData(int iD, String location) {
		this.ID = iD;
		this.Location = location;
	}
	public StoreData( String location) {
		this.Location = location;
	}

	public double getSalesMonth() {
		return salesMonth;
	}

	public void setSalesMonth(double salesMonth) {
		this.salesMonth = salesMonth;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

}
