package application;

public class CashierData {

	private int Order_ID;
	private int Items_ID;
	private double Unit_Price;
	private int Quantity;
	private double Disscount;
	private String Orderdate;

	public CashierData(int order_ID, int items_ID, double unit_Price, int quantity, double disscount,
			String orderdate) {
		this.Order_ID = order_ID;
		this.Items_ID = items_ID;
		this.Unit_Price = unit_Price;
		this.Quantity = quantity;
		this.Disscount = disscount;
		this.Orderdate = orderdate;
	}
	
	public CashierData(int items_ID, double unit_Price, int quantity, double disscount,
			String orderdate) {
		this.Items_ID = items_ID;
		this.Unit_Price = unit_Price;
		this.Quantity = quantity;
		this.Disscount = disscount;
		this.Orderdate = orderdate;
	}

	public int getOrder_ID() {
		return Order_ID;
	}

	public void setOrder_ID(int order_ID) {
		Order_ID = order_ID;
	}

	public int getItems_ID() {
		return Items_ID;
	}

	public void setItems_ID(int items_ID) {
		Items_ID = items_ID;
	}

	public double getUnit_Price() {
		return Unit_Price;
	}

	public void setUnit_Price(double unit_Price) {
		Unit_Price = unit_Price;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public double getDisscount() {
		return Disscount;
	}

	public void setDisscount(double disscount) {
		Disscount = disscount;
	}

	public String getOrderdate() {
		return Orderdate;
	}

	public void setOrderdate(String orderdate) {
		Orderdate = orderdate;
	}

}
