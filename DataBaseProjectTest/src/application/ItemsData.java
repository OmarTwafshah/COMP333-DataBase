package application;

public class ItemsData {

	private int ID;
	private String Name;
	private int DepartmentID;
	private int ProviderID;
	private double ItemsPrice;
	private String ProductionDate;
	private String ExpirationDate;
	private int Quantity;

	public ItemsData(int iD, String name, int departmentID, int providerID, double itemsPrice, String productionDate,
			String expirationDate, int quantity) {
		this.ID = iD;
		this.Name = name;
		this.DepartmentID = departmentID;
		this.ProviderID = providerID;
		this.ItemsPrice = itemsPrice;
		this.ProductionDate = productionDate;
		this.ExpirationDate = expirationDate;
		this.Quantity = quantity;
	}
	public ItemsData(String name, int departmentID, int providerID, double itemsPrice, String productionDate,
			String expirationDate, int quantity) {
		this.Name = name;
		this.DepartmentID = departmentID;
		this.ProviderID = providerID;
		this.ItemsPrice = itemsPrice;
		this.ProductionDate = productionDate;
		this.ExpirationDate = expirationDate;
		this.Quantity = quantity;
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

	public int getDepartmentID() {
		return DepartmentID;
	}

	public void setDepartmentID(int departmentID) {
		DepartmentID = departmentID;
	}

	public int getProviderID() {
		return ProviderID;
	}

	public void setProviderID(int providerID) {
		ProviderID = providerID;
	}

	public double getItemsPrice() {
		return ItemsPrice;
	}

	public void setItemsPrice(double itemsPrice) {
		ItemsPrice = itemsPrice;
	}

	public String getProductionDate() {
		return ProductionDate;
	}

	public void setProductionDate(String productionDate) {
		ProductionDate = productionDate;
	}

	public String getExpirationDate() {
		return ExpirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		ExpirationDate = expirationDate;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

}
