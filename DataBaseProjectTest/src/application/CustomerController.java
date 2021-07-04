package application;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class CustomerController {

	private Stage stage;
	private Scene scene;
	private Parent root;


	private ArrayList<CustomerData> data;
	private ObservableList<CustomerData> dataList;

	@FXML
	public void initialize() {
		data = new ArrayList<>();
		dataList = FXCollections.observableArrayList(data);
		TableData.setEditable(true);

		IDColumn.setCellFactory(TextFieldTableCell.<CustomerData, Integer>forTableColumn(new IntegerStringConverter()));
		IDColumn.setCellValueFactory(new PropertyValueFactory<CustomerData, Integer>("ID"));

		CityColumn.setCellValueFactory(new PropertyValueFactory<CustomerData, String>("City"));
		CityColumn.setCellFactory(TextFieldTableCell.<CustomerData>forTableColumn());
		CityColumn.setOnEditCommit((CellEditEvent<CustomerData, String> t) -> {
			((CustomerData) t.getTableView().getItems().get(t.getTablePosition().getRow())).setCity(t.getNewValue()); // display
																														// only

			updateCity(t.getRowValue().getID(), t.getNewValue());
		});
		getData();
		TableData.setItems(dataList);
	}

	@FXML
	private TableView<CustomerData> TableData;

	@FXML
	private TableColumn<CustomerData, Integer> IDColumn;

	@FXML
	private TableColumn<CustomerData, String> CityColumn;

	@FXML
	private Button update;

	@FXML
	private Button insert;

	@FXML
	private Button delete;

	@FXML
	private TextField oldID;

	@FXML
	private TextField City;

	@FXML
	private TextField newCity;

	@FXML
	private Button custBack;

	@FXML
	private TextField SearchFiled;

	@FXML
	private TextField deleteID;
	

	@FXML
	void custBackOnAction(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage thisStage = (Stage) node.getScene().getWindow();
		thisStage.close();
	}

	@FXML
	void deleteOnAction(ActionEvent event) {

		try {
			
				int id = Integer.parseInt(deleteID.getText());
				deleteRow(id);
			
		} catch(Exception e) {

		}
		refresh();
	}

	@FXML
	void insertOnAction(ActionEvent event) {
		CustomerData rc;
		rc = new CustomerData(City.getText());
		dataList.add(rc);
		insertData(rc);
		City.clear();
		refresh();
	}

	@FXML
	void updateOnAction(ActionEvent event) {

		try {
			if (oldID.getText() != null) {
				int id = Integer.parseInt(oldID.getText());
				if (newCity.getText().length() > 0) {
					System.out.println(newCity.getText());
					updateCity(id, newCity.getText());
					refresh();
				}
			}
		} catch (Exception e) {

		}

	}

	private void insertData(CustomerData rc) {

		try {
			System.out.println("Insert into Customers (City) values('"
					+ rc.getCity() + "')");

			Connector.a.connectDB();
			Connector.a.ExecuteStatement("Insert into Customers (City) values('"
					+ rc.getCity() + "')");
			Connector.a.connectDB().close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	

	public void updateCity(int Customer_ID, String City) {

		try {
			System.out.println("update  Customers set City = '" + City + "' where Customer_ID = " + Customer_ID);
			Connector.a.connectDB();
			Connector.a.ExecuteStatement(
					"update  Customers set City = '" + City + "' where Customer_ID = " + Customer_ID + ";");
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
			System.out.println("delete from  Customers where Customer_ID ="+id + ";");
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("delete from  Customers where Customer_ID ="+id + ";");
			Connector.a.connectDB().close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void getData() {
		String SQL = "select * from Customers";
		try {
			Connector.a.connectDB();
			Statement state = Connector.a.connectDB().createStatement();
			ResultSet rs = state.executeQuery(SQL);
			while(rs.next()) {
				CustomerData cd = new CustomerData(rs.getInt(1), rs.getString(2));
				dataList.add(cd);
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