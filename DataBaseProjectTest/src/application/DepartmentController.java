package application;

import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LongStringConverter;

public class DepartmentController {
	private Stage stage;
	private Scene scene;
	private Parent root;

	private ArrayList<DepartmentData> data;
	private ObservableList<DepartmentData> dataList;

	
	@FXML
	private TableView<DepartmentData> TableData;

	@FXML
	private TableColumn<DepartmentData, Integer> IDColumn;

	@FXML
	private TableColumn<DepartmentData, String> NameColumn;

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
	private TextField oldName;

	@FXML
	private TextField newID;

	@FXML
	private TextField Name;

	@FXML
	private TextField ID;

	@FXML
	private TextField newName;
	
	@FXML
    private Button depBack;
	@FXML
    private TextField name;

    @FXML
    private TextField deleteID;

    @FXML
	public void initialize() {
		data = new ArrayList<>();
		dataList = FXCollections.observableArrayList(data);
		TableData.setEditable(true);

		IDColumn.setCellFactory(TextFieldTableCell.<DepartmentData, Integer>forTableColumn(new IntegerStringConverter()));
		IDColumn.setCellValueFactory(new PropertyValueFactory<DepartmentData, Integer>("ID"));

		NameColumn.setCellValueFactory(new PropertyValueFactory<DepartmentData, String>("Name"));
		NameColumn.setCellFactory(TextFieldTableCell.<DepartmentData>forTableColumn());
		


		getData();
		TableData.setItems(dataList);
	}
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
			DepartmentData rc = new DepartmentData(name.getText());
			dataList.add(rc);
			insertData(rc);
			refresh();
			ID.clear();
			Name.clear();
		} catch (Exception e) {

		}
    }

    @FXML
    void updateOnAction(ActionEvent event) {
    	try {
			if(oldID.getText()!= null) {
				int id = Integer.parseInt(oldID.getText());
				if(newName.getText().length()>0) {
					System.out.println(newName.getText());
					updateName(id, newName.getText());
					refresh();
				}
			}
		} catch(Exception e) {

		}
    }
	
	@FXML
    void depBackOnAction(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage thisStage = (Stage) node.getScene().getWindow();
		thisStage.close();
    }
	
	private void insertData(DepartmentData rc) {

		try {
			System.out.println("Insert into Department (Name_DEP) values('" + rc.getName() + "')");

			Connector.a.connectDB();
			Connector.a.ExecuteStatement("Insert into Department (Name_DEP) values('" + rc.getName() + "')");
			Connector.a.connectDB().close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateName(int ID_num, String name) {

		try {
			System.out.println("update  Department set Name_DEP = '" + name + "' where DEP_ID = " + ID_num);
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update  Department set Name_DEP = '" + name + "' where DEP_ID = " + ID_num + ";");
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
			System.out.println("delete from  Department where DEP_ID ="+id + ";");
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("delete from  Department where DEP_ID ="+id + ";");
			Connector.a.connectDB().close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void getData() {
		String SQL = "select * from Department";
		try {
			Connector.a.connectDB();
			Statement state = Connector.a.connectDB().createStatement();
			ResultSet rs = state.executeQuery(SQL);
			while(rs.next()) {
				dataList.add(new DepartmentData(rs.getInt(1), rs.getString(2)));
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
