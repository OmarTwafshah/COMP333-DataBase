package application;

public class ProviderData {

	private int Provider_ID;
	private String Provider_Name;
	private double Provider_Price;
	private String DateP;

	public ProviderData(int provider_ID, String provider_Name, double provider_Price, String dateP) {
		this.Provider_ID = provider_ID;
		this.Provider_Name = provider_Name;
		this.Provider_Price = provider_Price;
		this.DateP = dateP;
	}
	
	public ProviderData(String provider_Name,double provider_Price, String dateP) {
		this.Provider_Name = provider_Name;
		this.Provider_Price = provider_Price;
		this.DateP = dateP;
	}

	public int getProvider_ID() {
		return Provider_ID;
	}

	public void setProvider_ID(int provider_ID) {
		Provider_ID = provider_ID;
	}

	public String getProvider_Name() {
		return Provider_Name;
	}

	public void setProvider_Name(String provider_Name) {
		Provider_Name = provider_Name;
	}

	public double getProvider_Price() {
		return Provider_Price;
	}

	public void setProvider_Price(double provider_Price) {
		Provider_Price = provider_Price;
	}

	public String getDateP() {
		return DateP;
	}

	public void setDateP(String dateP) {
		DateP = dateP;
	}

}