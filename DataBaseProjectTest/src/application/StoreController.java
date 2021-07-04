package application;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
import javafx.util.converter.IntegerStringConverter;

public class StoreController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private ArrayList<StoreData> data;
	private ObservableList<StoreData> dataList;
	
	@FXML
	public void initialize() {

		data = new ArrayList<>();
		dataList = FXCollections.observableArrayList(data);
		TableData.setEditable(true);
		IDColumn.setCellValueFactory(new PropertyValueFactory<StoreData, Integer>("ID"));
		IDColumn.setCellFactory(TextFieldTableCell.<StoreData, Integer>forTableColumn(new IntegerStringConverter()));
		
		LocationColumn.setCellValueFactory(new PropertyValueFactory<StoreData, String>("Location"));
		LocationColumn.setCellFactory(TextFieldTableCell.<StoreData>forTableColumn());
		
		getData();
		TableData.setItems(dataList);
	}
	
	@FXML
	private TableView<StoreData> TableData;

	@FXML
	private TableColumn<StoreData, Integer> IDColumn;

	@FXML
	private TableColumn<StoreData, String> LocationColumn;

	@FXML
	private TextField SearchFiled;

	@FXML
	private Button Update;

	@FXML
	private Button Insert;

	@FXML
	private Button Delete;

	@FXML
	private TextField oldID;

	@FXML
	private TextField OldLocation;

	@FXML
	private TextField newID;

	@FXML
	private TextField Location;

	@FXML
	private TextField ID;

	@FXML
	private TextField newLocation;
	
	@FXML
    private Button ware;

    @FXML
    void wareOnAction(ActionEvent event) {
    	Node node = (Node) event.getSource();
		Stage thisStage = (Stage) node.getScene().getWindow();
		thisStage.close();
    }
    @FXML
    private TextField deleteID;

    @FXML
    void deleteOnAction(ActionEvent event) {
    	try {
			if(deleteID.getText() != null) {
				int id = Integer.parseInt(deleteID.getText());
				deleteRow(id);
				refresh();
			}
		} catch(Exception e) {

		}
    }

    @FXML
    void insertOnAction(ActionEvent event) {
    	try {
			StoreData rc = new StoreData(Location.getText());
			dataList.add(rc);
			insertData(rc);
			ID.clear();
			Location.clear();
			refresh();
		} catch (Exception e) {

		}
    }

    @FXML
    void updateOnAction(ActionEvent event) {
    	try {
			if(oldID.getText()!= null) {
				int id = Integer.parseInt(oldID.getText());
				if(newLocation.getText().length()>0) {
					updateName(id, newLocation.getText());
					refresh();
				}
			}
		} catch(Exception e) {

		}
    }

    private void insertData(StoreData rc) {

		try {
			System.out.println("Insert into Store (location) values('" + rc.getLocation() + "')");

			Connector.a.connectDB();
			Connector.a.ExecuteStatement("Insert into Store (location) values('" + rc.getLocation() + "')");
			Connector.a.connectDB().close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateName(int ID_num, String location) {

		try {
			System.out.println("update  Store set location = '" + location + "' where Store_ID = " + ID_num);
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update  Store set location = '" + location + "' where Store_ID = " + ID_num + ";");
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
			System.out.println("delete from  Store where Store_ID ="+id + ";");
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("delete from  Store where Store_ID ="+id + ";");
			Connector.a.connectDB().close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void getData() {
		String SQL = "select * from Store";
		try {
			Connector.a.connectDB();
			Statement state = Connector.a.connectDB().createStatement();
			ResultSet rs = state.executeQuery(SQL);
			while (rs.next()) {
				dataList.add(new StoreData(rs.getInt(1), rs.getString(2)));
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
