package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class ItemsController {

	private ArrayList<ItemsData> data;
	private ObservableList<ItemsData> dataList;

	@FXML
	public void initialize() {
		data = new ArrayList<>();
		dataList = FXCollections.observableArrayList(data);
		TableData.setEditable(true);

		IDColumn.setCellFactory(TextFieldTableCell.<ItemsData, Integer>forTableColumn(new IntegerStringConverter()));
		IDColumn.setCellValueFactory(new PropertyValueFactory<ItemsData, Integer>("ID"));

		NameColumn.setCellValueFactory(new PropertyValueFactory<ItemsData, String>("Name"));
		NameColumn.setCellFactory(TextFieldTableCell.<ItemsData>forTableColumn());

		DepartmentIDColumn.setCellValueFactory(new PropertyValueFactory<ItemsData, Integer>("DepartmentID"));
		DepartmentIDColumn.setCellFactory(TextFieldTableCell.<ItemsData, Integer>forTableColumn(new IntegerStringConverter()));


		ProviderIDColumn.setCellValueFactory(new PropertyValueFactory<ItemsData, Integer>("ProviderID"));
		ProviderIDColumn.setCellFactory(TextFieldTableCell.<ItemsData, Integer>forTableColumn(new IntegerStringConverter()));

		ItemsPriceColumn.setCellValueFactory(new PropertyValueFactory<ItemsData, Double>("ItemsPrice"));
		ItemsPriceColumn.setCellFactory(TextFieldTableCell.<ItemsData, Double>forTableColumn(new DoubleStringConverter()));


		ProductionDateColumn.setCellValueFactory(new PropertyValueFactory<ItemsData, String>("ProductionDate"));
		ProductionDateColumn.setCellFactory(TextFieldTableCell.<ItemsData>forTableColumn());


		ExpirationDateColumn.setCellValueFactory(new PropertyValueFactory<ItemsData, String>("ExpirationDate"));
		ExpirationDateColumn.setCellFactory(TextFieldTableCell.<ItemsData>forTableColumn());


		QuantityCoulmn.setCellValueFactory(new PropertyValueFactory<ItemsData, Integer>("Quantity"));
		QuantityCoulmn.setCellFactory(TextFieldTableCell.<ItemsData, Integer>forTableColumn(new IntegerStringConverter()));

		getData();
		TableData.setItems(dataList);
	}

	@FXML
	private TableView<ItemsData> TableData;

	@FXML
	private TableColumn<ItemsData, Integer> IDColumn;

	@FXML
	private TableColumn<ItemsData, String> NameColumn;

	@FXML
	private TableColumn<ItemsData, Integer> DepartmentIDColumn;

	@FXML
	private TableColumn<ItemsData, Integer> ProviderIDColumn;

	@FXML
	private TableColumn<ItemsData, Double> ItemsPriceColumn;

	@FXML
	private TableColumn<ItemsData, String> ProductionDateColumn;

	@FXML
	private TableColumn<ItemsData, String> ExpirationDateColumn;

	@FXML
	private TableColumn<ItemsData, Integer> QuantityCoulmn;

	@FXML
	private Button Update;

	@FXML
	private TextField oldID;

	@FXML
	private TextField newName;

	@FXML
	private TextField newDepartmentID;

	@FXML
	private TextField newProviderID;

	@FXML
	private TextField newItemsPrice;

	@FXML
	private TextField SearchFiled;

	@FXML
	private TextField ID;

	@FXML
	private TextField newProductionDate;

	@FXML
	private TextField newExpirationDate;

	@FXML
	private Button Delete;

	@FXML
	private Button Insert;

	@FXML
	private TextField newQuantuty;

	@FXML
	private Button itemsBack;

	@FXML
	private TextField addName;

	@FXML
	private TextField addDepID;

	@FXML
	private TextField addProID;

	@FXML
	private TextField addItemsPrice;

	@FXML
	private TextField addProductionDate;

	@FXML
	private TextField addExpirationDate;

	@FXML
	private TextField addQuantuty;

	@FXML
	private TextField addID;

	@FXML
	void itemsBackOnAction(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage thisStage = (Stage) node.getScene().getWindow();
		thisStage.close();
	}

	@FXML
	void AddSetAction(ActionEvent event) {
		ItemsData rc;
		rc = new ItemsData(addName.getText(), Integer.parseInt(addDepID.getText()),
				Integer.parseInt(addProID.getText()), Double.parseDouble(addItemsPrice.getText()),
				addProductionDate.getText(), addExpirationDate.getText(), Integer.parseInt(addQuantuty.getText()));
		dataList.add(rc);
		insertData(rc);
		addID.clear();
		addName.clear();
		addDepID.clear();
		addProID.clear();
		addItemsPrice.clear();
		addProductionDate.clear();
		addExpirationDate.clear();
		addQuantuty.clear();
		refresh();
	}

	@FXML
	void DeleteSetAction(ActionEvent event) {
		try {

			int id = Integer.parseInt(ID.getText());
			deleteRow(id);

		} catch(Exception e) {

		}
		refresh();
	}

	@FXML
	void updateOnAction(ActionEvent event) {
		try {
			if (oldID.getText() != null) {
				int id = Integer.parseInt(oldID.getText());
				if (newName.getText().length() > 0) {
					System.out.println(newName.getText());
					updateName(id, newName.getText());
				}
				if (newDepartmentID.getText().length() > 0) {
					System.out.println(Integer.parseInt(newDepartmentID.getText()));
					updateDepartmentID(id, Integer.parseInt(newDepartmentID.getText()));
				}
				if (newProviderID.getText().length() > 0) {
					System.out.println(Integer.parseInt(newProviderID.getText()));
					updateProviderID(id, Integer.parseInt(newProviderID.getText()));

				}
				if (newItemsPrice.getText().length() > 0) {
					double ItemsPrice = Double.parseDouble(newItemsPrice.getText());
					updateItemsPrice(id, ItemsPrice);
				}
				if (newProductionDate.getText().length() > 0) {
					updateProductionDate(id, newProductionDate.getText());
				}

				if (newExpirationDate.getText().length() > 0) {
					updateExpirationDate(id, newExpirationDate.getText());
				}

				if (newQuantuty.getText().length() > 0) {
					int Quantuty = Integer.parseInt(newQuantuty.getText());
					updateQuantuty(id, Quantuty);
				}
				refresh();
			}
		} catch (

				Exception e) {

		}

	}

	private void insertData(ItemsData rc) {

		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			Date myDate = new Date();
			java.sql.Date sqlDate;
			try {
				myDate = formatter.parse(rc.getExpirationDate());

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sqlDate = new java.sql.Date(myDate.getTime());

			DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");

			Date myDate2 = new Date();
			java.sql.Date sqlDate2;
			try {
				myDate2 = formatter2.parse(rc.getProductionDate());

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sqlDate2 = new java.sql.Date(myDate2.getTime());

			System.out.println(
					"Insert into Items (itemsName,DEP_ID,Provider_ID,Items_Price, production_Date, expiraation_Date , quantity) values('" + rc.getName() + "','" + rc.getDepartmentID() + "','"
							+ rc.getProviderID() + "','" + rc.getItemsPrice() + "','" + sqlDate2 + "','" + sqlDate
							+ "','" + rc.getQuantity() + "')");

			Connector.a.connectDB();
			Connector.a.ExecuteStatement(
					"Insert into Items (itemsName,DEP_ID,Provider_ID,Items_Price, production_Date, expiraation_Date , quantity) values('" + rc.getName() + "','" + rc.getDepartmentID() + "','"
							+ rc.getProviderID() + "','" + rc.getItemsPrice() + "','" + sqlDate2 + "','" + sqlDate
							+ "','" + rc.getQuantity() + "')");
			Connector.a.connectDB().close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void deleteRow(int id) {

		try {
			System.out.println("delete from  Items where Items_ID ="+id + ";");
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("delete from  Items where Items_ID ="+id + ";");
			Connector.a.connectDB().close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateName(int ID, String name) {

		try {
			System.out.println("update  Items set itemsName = '" + name + "' where Items_ID = " + ID);
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update  Items set itemsName = '" + name + "' where Items_ID = " + ID + ";");
			Connector.a.connectDB().close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateDepartmentID(int ID, int DEP_ID) {

		try {
			System.out.println("update  Items set DEP_ID = " + DEP_ID + " where Items_ID = " + ID);
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update  Items set DEP_ID = " + DEP_ID + " where  Items_ID = " + ID + ";");
			Connector.a.connectDB().close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateProductionDate(int ID, String ProductionDate) {
		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			Date myDate = new Date();
			java.sql.Date sqlDate;
			try {
				myDate = formatter.parse(ProductionDate);

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sqlDate = new java.sql.Date(myDate.getTime());
			System.out.println("update  Items set production_Date = " + sqlDate + " where Items_ID = " + ID);
			Connector.a.connectDB();
			Connector.a.ExecuteStatement(
					"update  Items set production_Date = " + sqlDate + " where Items_ID = " + ID + ";");
			Connector.a.connectDB().close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateExpirationDate(int ID, String ExpirationDate) {
		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			Date myDate = new Date();
			java.sql.Date sqlDate;
			try {
				myDate = formatter.parse(ExpirationDate);

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sqlDate = new java.sql.Date(myDate.getTime());
			System.out.println("update  Items set expiraation_Date = " + sqlDate + " where Items_ID = " + ID);
			Connector.a.connectDB();
			Connector.a.ExecuteStatement(
					"update  Items set expiraation_Date = " + sqlDate + " where Items_ID = " + ID + ";");
			Connector.a.connectDB().close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateProviderID(int ID, int ProviderID) {
		try {
			System.out.println("update  Items set Provider_ID = " + ProviderID + " where Items_ID = " + ID);
			Connector.a.connectDB();
			Connector.a.ExecuteStatement(
					"update  Items set Provider_ID = " + ProviderID + " where  Items_ID= " + ID + ";");
			Connector.a.connectDB().close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateItemsPrice(int ID, double Items_Price) {
		try {
			System.out.println("update  Items set Items_Price = " + Items_Price + " where Items_ID = " + ID);
			Connector.a.connectDB();
			Connector.a.ExecuteStatement(
					"update  Items set Items_Price = " + Items_Price + " where  Items_ID= " + ID + ";");
			Connector.a.connectDB().close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateQuantuty(int ID, int quantity) {
		try {
			System.out.println("update  Items set quantity = " + quantity + " where Items_ID = " + ID);
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update  Items set quantity = " + quantity + " where  Items_ID= " + ID + ";");
			Connector.a.connectDB().close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void getData() {
		String SQL = "select * from Items";
		try {
			Connector.a.connectDB();
			Statement state = Connector.a.connectDB().createStatement();
			ResultSet rs = state.executeQuery(SQL);
			while (rs.next()) {
				dataList.add(new ItemsData(rs.getInt(1), rs.getString(7), rs.getInt(2), rs.getInt(9), rs.getDouble(4),
						rs.getString(5), rs.getString(6), rs.getInt(10)));
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

	public void refresh() {
		TableData.getItems().clear();
		getData();
		TableData.setItems(dataList);
	}

}