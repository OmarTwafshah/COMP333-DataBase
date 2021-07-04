package application;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class CashierController {

	private Stage stage;
	private Scene scene;
	private Parent root;

	private ArrayList<CashierData> data;
	private ObservableList<CashierData> dataList;

	@FXML
	private TableView<CashierData> TableData;

	@FXML
	private TableColumn<CashierData, Integer> OrderIDColumn;

	@FXML
	private TableColumn<CashierData, Integer> ItemsIDColumn;

	@FXML
	private TableColumn<CashierData, Double> unitPriceColumn;

	@FXML
	private TableColumn<CashierData, Integer> QuantityColumn;

	@FXML
	private TableColumn<CashierData, Double> DisscountColumn;

	@FXML
	private TableColumn<CashierData, String> OrderDateColumn;

	@FXML
	private Button Update;

	@FXML
	private TextField OldOrderID;

	@FXML
	private TextField newQuantity;

	@FXML
	private TextField newDisscount;

	@FXML
	private TextField SearchFiled;

	@FXML
	private TextField IDOrder;

	@FXML
	private TextField IDitems;

	@FXML
	private Button Return;

	@FXML
	private Button BuyAll;

	@FXML
	private Button LogOut;

	@FXML
	private TextField addOrderID;

	@FXML
	private TextField addItemsID;

	@FXML
	private TextField addQuantity;

	@FXML
	private TextField addDisscount;

	@FXML
	private Button addOrder;

	@FXML
	private TextField oldItemsID;


	@FXML
	public void initialize() {
		data = new ArrayList<>();

		dataList = FXCollections.observableArrayList(data);
		TableData.setEditable(true);
		OrderIDColumn.setCellValueFactory(new PropertyValueFactory<CashierData, Integer>("Order_ID"));
		OrderIDColumn.setCellFactory(TextFieldTableCell.<CashierData, Integer>forTableColumn(new IntegerStringConverter()));

		ItemsIDColumn.setCellFactory(TextFieldTableCell.<CashierData, Integer>forTableColumn(new IntegerStringConverter()));
		ItemsIDColumn.setCellValueFactory(new PropertyValueFactory<CashierData, Integer>("Items_ID"));
		
		unitPriceColumn.setCellFactory(TextFieldTableCell.<CashierData, Double>forTableColumn(new DoubleStringConverter()));
		unitPriceColumn.setCellValueFactory(new PropertyValueFactory<CashierData, Double>("Unit_Price"));
		
		QuantityColumn.setCellFactory(TextFieldTableCell.<CashierData, Integer>forTableColumn(new IntegerStringConverter()));
		QuantityColumn.setCellValueFactory(new PropertyValueFactory<CashierData, Integer>("Quantity"));
		
		DisscountColumn.setCellFactory(TextFieldTableCell.<CashierData, Double>forTableColumn(new DoubleStringConverter()));
		DisscountColumn.setCellValueFactory(new PropertyValueFactory<CashierData, Double>("Disscount"));
		
		OrderDateColumn.setCellFactory(TextFieldTableCell.<CashierData>forTableColumn());
		OrderDateColumn.setCellValueFactory(new PropertyValueFactory<CashierData, String>("Orderdate"));

		getData();
		dataList = FXCollections.observableArrayList(data);
	}

	@FXML
	void BuyAllAction(ActionEvent event) throws ClassNotFoundException, SQLException {

		String totalString = viewValue(
				"select sum(O.Unit_Price - (O.Unit_Price * O.Disscount)) AS Total from OrderDetails O Where O.Order_ID ="
						+ OldOrderID.getText() + ";");
		double total = Double.parseDouble(totalString);

		InvoiceData invdata = new InvoiceData(Integer.parseInt(OldOrderID.getText()), total);

		InvoiceController.data = invdata;

		try {
			root = FXMLLoader.load(getClass().getResource("Invoice.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stage = new Stage();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

		Connector.a.connectDB();
		Connector.a.ExecuteStatement("DELETE FROM OrderDetails WHERE Order_ID = " + OldOrderID.getText() + ";");
		Connector.a.connectDB().close();
		System.out.println("Connection closed");

	}

	@FXML
	void ReturnOnAvtion(ActionEvent event) {
		try {
			if (IDOrder.getText() != null && IDitems.getText() != null) {
				int idOrder = Integer.parseInt(IDOrder.getText());
				int idItems = Integer.parseInt(IDitems.getText());

				deleteRow(idOrder, idItems);

				IDOrder.clear();
				IDitems.clear();
			}
		} catch (Exception e) {

		}
	}

	@FXML
	void UpdateOnAction(ActionEvent event) throws ClassNotFoundException, SQLException {
		String command = "select Items.Items_Price FROM items , OrderDetails where Items.Items_ID = OrderDetails.Items_ID ;";
		String vlaue = viewValue(command);
		int UnitPrice = Integer.parseInt(vlaue);
		if (IDOrder.getText() != null && IDitems.getText() != null && newDisscount.getText() != null
				&& newQuantity != null) {

			int idOrder = Integer.parseInt(IDOrder.getText());
			int idItems = Integer.parseInt(IDitems.getText());

			deleteRow(idOrder, idItems);

			CashierData rc;
			Date date = Calendar.getInstance().getTime();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
			String strDate = dateFormat.format(date);
			rc = new CashierData(idOrder, idItems, UnitPrice, Integer.parseInt(newQuantity.getText()),
					Double.parseDouble(newDisscount.getText()), strDate);
			dataList.add(rc);
			insertData(rc);
			addOrderID.clear();
			addItemsID.clear();
			addItemsID.clear();
			addQuantity.clear();
			addDisscount.clear();

		}
	}

	@FXML
	void addOnAction(ActionEvent event) throws ClassNotFoundException, SQLException {
		PreparedStatement command = Connector.a.connectDB().prepareStatement("select I.Items_Price FROM Items I, OrderDetails O where I.Item_ID = ? AND I.Items_ID = O.Items_ID ;");
		command.setString(1, addOrderID.getText());
		ResultSet rs = command.executeQuery();
		if(rs.next()) {
			int UnitPrice = rs.getInt(1);
			System.out.println(UnitPrice);
			CashierData rc;
			Date date = Calendar.getInstance().getTime();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
			String strDate = dateFormat.format(date);
			rc = new CashierData(Integer.parseInt(addOrderID.getText()), Integer.parseInt(addItemsID.getText()), UnitPrice,
					Integer.parseInt(addQuantity.getText()), Double.parseDouble(addDisscount.getText()), strDate);
			dataList.add(rc);
			insertData(rc);
		}
		
		
		addOrderID.clear();
		addItemsID.clear();
		addItemsID.clear();
		addQuantity.clear();
		addDisscount.clear();

	}

	private void insertData(CashierData rc) {

		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

			Date myDate = new Date();
			java.sql.Date sqlDate;
			try {
				myDate = formatter.parse(rc.getOrderdate());

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sqlDate = new java.sql.Date(myDate.getTime());

			System.out.println(
					"Insert into OrderDetails (Order_ID,Items_ID,Unit_Price,Quantity,DisscountوOrderdate) values("
							+ rc.getOrder_ID() + ",'" + rc.getItems_ID() + "','" + rc.getUnit_Price() + "','"
							+ rc.getQuantity() + "','" + rc.getDisscount() + "','" + sqlDate + "')");

			Connector.a.connectDB();
			Connector.a.ExecuteStatement(
					"Insert into OrderDetails (Order_ID,Items_ID,Unit_Price,Quantity,DisscountوOrderdate) values("
							+ rc.getOrder_ID() + ",'" + rc.getItems_ID() + "','" + rc.getUnit_Price() + "','"
							+ rc.getQuantity() + "','" + rc.getDisscount() + "','" + sqlDate + "')");
			Connector.a.connectDB().close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void getData() {
		String SQL = "select * from Invoice";
		try {
			Connector.a.connectDB();
			Statement state = Connector.a.connectDB().createStatement();
			ResultSet rs = state.executeQuery(SQL);
			while (rs.next()) {
				dataList.add(new CashierData(Integer.parseInt(rs.getString(1)), Integer.parseInt(rs.getString(2)),
						Double.parseDouble(rs.getString(3)), Integer.parseInt(rs.getString(4)),
						Double.parseDouble(rs.getString(5)), rs.getString(6)));
			}
			rs.close();
			state.close();
			Connector.a.connectDB().close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deleteRow(int idOrder, int idItems) {

		try {
			System.out
					.println("delete from  OrderDetails where Order_ID =" + idOrder + "AND Items_ID=" + idItems + ";");
			Connector.a.connectDB();
			Connector.a.ExecuteStatement(
					"delete from  OrderDetails where Order_ID =" + idOrder + "AND Items_ID=" + idItems + ";");
			Connector.a.connectDB().close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	private String viewValue(String command) throws SQLException, ClassNotFoundException {
		String value = null;
		Statement stmt = null;

		try {
			stmt = Connector.a.connectDB().createStatement();
			ResultSet rs = stmt.executeQuery(command);

			while (rs.next())
				value = rs.getString(1);
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			if (stmt != null) {
				stmt.close();
			}
		}

		return value;

	}
	@FXML
	void BackSample(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stage = new Stage();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		Node node = (Node) event.getSource();
		Stage thisStage = (Stage) node.getScene().getWindow();
		thisStage.close();
	}
	
	
	public void refresh() {
		TableData.getItems().clear();
		getData();
		TableData.setItems(dataList);
	}

}
