package application;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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

public class ChildrenController {

	private ArrayList<ChildrenData> data;
	private ObservableList<ChildrenData> dataList;



	@FXML
	private TableView<ChildrenData> TableData;

	@FXML
	private TableColumn<ChildrenData, Integer> EMPIDCoulmn;

	@FXML
	private TableColumn<ChildrenData, String> NameColumn;

	@FXML
	private TableColumn<ChildrenData, Integer> serialNumberColumn;

	@FXML
	private TableColumn<ChildrenData, String> DateBirthCoulmn;

	@FXML
	private TableColumn<ChildrenData, String> GenderColumn;

	@FXML
	private Button Update;

	@FXML
	private TextField oldEmpID;

	@FXML
	private TextField newName;

	@FXML
	private TextField OldSerNumber;

	@FXML
	private TextField newDateBirth;

	@FXML
	private TextField newGender;

	@FXML
	private TextField SearchFiled;

	@FXML
	private TextField addEMPID;

	@FXML
	private TextField addName;

	@FXML
	private TextField addSerNumber;

	@FXML
	private TextField addDateBirth;

	@FXML
	private TextField addGender;

	@FXML
	private Button Delete;

	@FXML
	private Button Insert;

	@FXML
	private Button providerBack;

	@FXML
	private TextField ID;

	@FXML
	private TextField serialNumber;
	
	@FXML
	public void initialize() {
		data = new ArrayList<>();
		dataList = FXCollections.observableArrayList(data);
		TableData.setEditable(true);
		EMPIDCoulmn.setCellValueFactory(new PropertyValueFactory<ChildrenData, Integer>("Emp_ID"));
		NameColumn.setCellValueFactory(new PropertyValueFactory<ChildrenData, String>("Name"));
		serialNumberColumn.setCellValueFactory(new PropertyValueFactory<ChildrenData, Integer>("SerialNumber"));
		DateBirthCoulmn.setCellValueFactory(new PropertyValueFactory<ChildrenData, String>("DateBirth"));
		GenderColumn.setCellValueFactory(new PropertyValueFactory<ChildrenData, String>("Gender"));

		getData();
		TableData.setItems(dataList);
	}

	@FXML
	void ChildrenBackOnAction(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage thisStage = (Stage) node.getScene().getWindow();
		thisStage.close();
	}

	@FXML
	void DeleteSetAction(ActionEvent event) {
		try {
			if (ID.getText() != null && serialNumber.getText() != null) {
				int idEMP = Integer.parseInt(ID.getText());
				int idserialNumber = Integer.parseInt(serialNumber.getText());

				deleteRow(idEMP, idserialNumber);

				ID.clear();
				serialNumber.clear();
			}
		} catch (Exception e) {

		}
	}

	@FXML
	void UpdateSetAction(ActionEvent event) {
		try {
			if (oldEmpID.getText() != null && OldSerNumber.getText() != null) {
				int idEMP = Integer.parseInt(oldEmpID.getText());
				int SerNumber = Integer.parseInt(OldSerNumber.getText());

				if (newName.getText().length() > 0) {
					System.out.println(newName.getText());
					updateName(idEMP, SerNumber, newName.getText());
				}
				if (newDateBirth.getText().length() > 0) {
					System.out.println(newDateBirth.getText());
					updateBDate(idEMP, SerNumber, newDateBirth.getText());
				}
				if (newGender.getText().length() > 0) {
					System.out.println(newGender.getText());
					updateGender(idEMP, SerNumber, newGender.getText());
				}
			}
		} catch (Exception e) {

		}
	}

	@FXML
	void addSetAction(ActionEvent event) {
		ChildrenData rc;
		rc = new ChildrenData(Integer.parseInt(addEMPID.getText()), addName.getText(),
				Integer.parseInt(addSerNumber.getText()), addDateBirth.getText(), addGender.getText());
		dataList.add(rc);
		insertData(rc);
		addEMPID.clear();
		addName.clear();
		addSerNumber.clear();
		addDateBirth.clear();
		addGender.clear();
	}

	private void deleteRow(int idEMP, int idserialNumber) {

		try {
			System.out.println(
					"delete from  Children where EM_ID =" + idEMP + " AND Serial_Number =" + idserialNumber + ";");
			Connector.a.connectDB();
			Connector.a.ExecuteStatement(
					"delete from  Children where EM_ID =" + idEMP + " AND Serial_Number =" + idserialNumber + ";");
			Connector.a.connectDB().close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void insertData(ChildrenData rc) {

		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			Date myDate = new Date();
			java.sql.Date sqlDate;
			try {
				myDate = formatter.parse(rc.getDateBirth());

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sqlDate = new java.sql.Date(myDate.getTime());

			System.out.println("Insert into Children (EM_ID,Serial_Number,Gender,NameCh,Date_Of_Birth	) values("
					+ rc.getEmp_ID() + ",'" + rc.getSerialNumber() + "','" + rc.getGender() + "','" + rc.getName()
					+ "','" + sqlDate + "')");

			Connector.a.connectDB();
			Connector.a.ExecuteStatement(
					"Insert into Children (EM_ID,Serial_Number,Gender,NameCh,Date_Of_Birth	) values(" + rc.getEmp_ID()
							+ ",'" + rc.getSerialNumber() + "','" + rc.getGender() + "','" + rc.getName() + "','"
							+ sqlDate + "')");
			Connector.a.connectDB().close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateName(int idEMP, int SerNumber, String name) {

		try {
			System.out.println("update  Children set NameCh = '" + name + "' where EM_ID = " + idEMP
					+ "' AND Serial_Number = '" + SerNumber + ";");
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update  Children set NameCh = '" + name + "' where EM_ID = " + idEMP
					+ "' AND Serial_Number = '" + SerNumber + ";");
			Connector.a.connectDB().close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateGender(int idEMP, int SerNumber, String Gender) {

		try {
			System.out.println("update  Children set Gender = '" + Gender + "' where EM_ID = " + idEMP
					+ "' AND Serial_Number = '" + SerNumber + ";");
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update  Children set Gender = '" + Gender + "' where EM_ID = " + idEMP
					+ "' AND Serial_Number = '" + SerNumber + ";");
			Connector.a.connectDB().close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateBDate(int idEMP, int SerNumber, String date) {
		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			Date myDate = new Date();
			java.sql.Date sqlDate;
			try {
				myDate = formatter.parse(date);

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sqlDate = new java.sql.Date(myDate.getTime());
			System.out.println("update  Children set DateP = " + sqlDate + "' where EM_ID = " + idEMP
					+ "' AND Serial_Number = '" + SerNumber + ";");
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update  Children set DateP = " + sqlDate + "' where EM_ID = " + idEMP
					+ "' AND Serial_Number = '" + SerNumber + ";");
			Connector.a.connectDB().close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void getData() {
		String SQL = "select * from Children";
		try {
			Connector.a.connectDB();
			Statement state = Connector.a.connectDB().createStatement();
			ResultSet rs = state.executeQuery(SQL);
			while (rs.next()) {
				dataList.add(new ChildrenData(Integer.parseInt(rs.getString(1)), rs.getString(2),
						Integer.parseInt(rs.getString(3)), rs.getString(4), rs.getString(5)));
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
}
