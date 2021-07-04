package application;

public class InvoiceData {

	private int Order_ID;
	private double TotalpaymentDisscount;

	public InvoiceData(int order_ID, double totalpaymentDisscount) {
		this.Order_ID = order_ID;
		this.TotalpaymentDisscount = totalpaymentDisscount;
	}

	public int getOrder_ID() {
		return Order_ID;
	}

	public void setOrder_ID(int order_ID) {
		Order_ID = order_ID;
	}

	public double getTotalpaymentDisscount() {
		return TotalpaymentDisscount;
	}

	public void setTotalpaymentDisscount(double totalpaymentDisscount) {
		TotalpaymentDisscount = totalpaymentDisscount;
	}

}
