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

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;

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
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LongStringConverter;

public class EmployeeController {
	private Stage stage;
	private Scene scene;
	private Parent root;

	private ArrayList<EmployeeData> data;
	private ObservableList<EmployeeData> dataList;



	@FXML
	private TableView<EmployeeData> TableData;

	@FXML
	private TableColumn<EmployeeData, Integer> IDColumn;

	@FXML
	private TableColumn<EmployeeData, String> NameColumn;

	@FXML
	private TableColumn<EmployeeData, String> DateBirthColumn;

	@FXML
	private TableColumn<EmployeeData, String> GenderColumn;

	@FXML
	private TableColumn<EmployeeData, Long> PhoneNumberColumn;

	@FXML
	private TableColumn<EmployeeData, String> AddressColumn;

	@FXML
	private TableColumn<EmployeeData, Double> SalaryColumn;

	@FXML
	private Button Update;

	@FXML
	private TextField oldID;

	@FXML
	private TextField newName;

	@FXML
	private TextField newDateBirth;

	@FXML
	private TextField NewGender;

	@FXML
	private TextField newPHoneNumber;

	@FXML
	private TextField SearchFiled;

	@FXML
	private TextField ID;

	@FXML
	private TextField Name;

	@FXML
	private TextField OldAddress;

	@FXML
	private TextField OldSalary;

	@FXML
	private TextField nnewAddress;

	@FXML
	private TextField newSalary;

	@FXML
	private Button Delete;

	@FXML
	private TextField DateBirth;

	@FXML
	private TextField Gender;

	@FXML
	private TextField PhoneNumber;

	@FXML
	private TextField Address;

	@FXML
	private TextField Salary;

	@FXML
	private Button Displary;

	@FXML
	private Button employeeBack;
	
	@FXML
	private Button add;

	@FXML
	private TextField addName;

	@FXML
	private TextField addBD;

	@FXML
	private TextField addGender;

	@FXML
	private TextField addPN;

	@FXML
	private TextField addAddress;

	@FXML
	private TextField addSalary;

	

	@FXML
	public void initialize() {
		data = new ArrayList<>();
		dataList = FXCollections.observableArrayList(data);
		TableData.setEditable(true);

		IDColumn.setCellFactory(TextFieldTableCell.<EmployeeData, Integer>forTableColumn(new IntegerStringConverter()));
		IDColumn.setCellValueFactory(new PropertyValueFactory<EmployeeData, Integer>("ID"));

		NameColumn.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("Name"));
		NameColumn.setCellFactory(TextFieldTableCell.<EmployeeData>forTableColumn());
		NameColumn.setOnEditCommit(        
				(CellEditEvent<EmployeeData, String> t) -> {
					((EmployeeData) t.getTableView().getItems().get(t.getTablePosition().getRow())).setGender(t.getNewValue()); //display only

					updateName( t.getRowValue().getID(),t.getNewValue());
				});

		DateBirthColumn.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("DateBirth"));
		DateBirthColumn.setCellFactory(TextFieldTableCell.<EmployeeData>forTableColumn());
		DateBirthColumn.setOnEditCommit(        
				(CellEditEvent<EmployeeData, String> t) -> {
					((EmployeeData) t.getTableView().getItems().get(t.getTablePosition().getRow())).setGender(t.getNewValue()); //display only

					updateBDate( t.getRowValue().getID(),t.getNewValue());
				});

		GenderColumn.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("Gender"));
		GenderColumn.setCellFactory(TextFieldTableCell.<EmployeeData>forTableColumn());
		GenderColumn.setOnEditCommit(        
				(CellEditEvent<EmployeeData, String> t) -> {
					((EmployeeData) t.getTableView().getItems().get(t.getTablePosition().getRow())).setGender(t.getNewValue()); //display only

					updateGender( t.getRowValue().getID(),t.getNewValue().toLowerCase().charAt(0));
				});

		PhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<EmployeeData, Long>("PhoneNumber"));
		PhoneNumberColumn.setCellFactory(TextFieldTableCell.<EmployeeData, Long>forTableColumn(new LongStringConverter()));
		PhoneNumberColumn.setOnEditCommit(        
				(CellEditEvent<EmployeeData, Long> t) -> {
					((EmployeeData) t.getTableView().getItems().get(t.getTablePosition().getRow())).setPhoneNumber(t.getNewValue()); //display only

					updatePhone_num( t.getRowValue().getID(),t.getNewValue());
				});

		AddressColumn.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("Address"));
		AddressColumn.setCellFactory(TextFieldTableCell.<EmployeeData>forTableColumn());
		AddressColumn.setOnEditCommit(        
				(CellEditEvent<EmployeeData, String> t) -> {
					((EmployeeData) t.getTableView().getItems().get(t.getTablePosition().getRow())).setGender(t.getNewValue()); //display only

					updateAddress( t.getRowValue().getID(),t.getNewValue());
				});

		SalaryColumn.setCellValueFactory(new PropertyValueFactory<EmployeeData, Double>("Salary"));
		SalaryColumn.setCellFactory(TextFieldTableCell.<EmployeeData, Double>forTableColumn(new DoubleStringConverter()));
		SalaryColumn.setOnEditCommit(        
				(CellEditEvent<EmployeeData, Double> t) -> {
					((EmployeeData) t.getTableView().getItems().get(t.getTablePosition().getRow())).setSalary(t.getNewValue()); //display only

					updateSalary( t.getRowValue().getID(),t.getNewValue());
				});


		getData();
		TableData.setItems(dataList);
	}

	@FXML
	void employeeBackOnAction(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage thisStage = (Stage) node.getScene().getWindow();
		thisStage.close();
	}

	@FXML
	void addOnAction(ActionEvent event) {
		try {
			EmployeeData rc;
			rc = new EmployeeData(addBD.getText(), addAddress.getText(),
					addName.getText(), addGender.getText(), Long.valueOf(addPN.getText()),
					Double.valueOf(addSalary.getText()));
			dataList.add(rc);
			insertData(rc);
			refresh();
			addName.clear();
			addBD.clear();
			addGender.clear();
			addPN.clear();
			addAddress.clear();
			addSalary.clear();
		} catch (Exception e) {

		}
	}

	@FXML
	void deleteOnAction(ActionEvent event) {
		try {
			if(ID.getText() != null) {
				int id = Integer.parseInt(ID.getText());
				deleteRow(id);
			}
		} catch(Exception e) {

		}
		refresh();

	}

	@FXML
	void updateOnAction(ActionEvent event) {
		try {
			if(oldID.getText()!= null) {
				int id = Integer.parseInt(oldID.getText());
				if(newName.getText().length()>0) {
					System.out.println(newName.getText());
					updateName(id, newName.getText());
				}
				if(newDateBirth.getText().length()>0) {
					System.out.println(newDateBirth.getText());
					updateBDate(id, newDateBirth.getText());
				}
				if(NewGender.getText().length()>0) {
					char gen = NewGender.getText().toUpperCase().charAt(0);
					if(gen == 'M' || gen == 'F') {
						updateGender(id, gen);
					}
				}
				if(newPHoneNumber.getText().length()>0) {
					long num = Long.parseLong(newPHoneNumber.getText());
					updatePhone_num(id, num);
				}
				if(nnewAddress.getText().length()>0) {
					updateAddress(id, nnewAddress.getText());
				}
				if(newSalary.getText().length()>0) {
					double salary = Double.parseDouble(newSalary.getText());
					updateSalary(id, salary);
				}
				refresh();
			}
		} catch(Exception e) {

		}
		refresh();

	}

	private void insertData(EmployeeData rc) {

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


			System.out.println(
					"Insert into Employees (NameEM,Date_Of_Birth,Gender,Phone_Number, Address, Salary) values("
							+ rc.getName() + ",'" + sqlDate + "','" + rc.getGender() + "','"
							+ rc.getPhoneNumber() + "','" + rc.getAddress() + "','" + rc.getSalary() + "')");

			Connector.a.connectDB();
			String sql = "Insert into Employees (NameEM,Date_Of_Birth,Gender,Phone_Number, Address, SalaryEmployees) values(?,?,?,?,?,?)";
			PreparedStatement ps = Connector.a.connectDB().prepareStatement(sql);
			ps.setString(1, rc.getName());
			ps.setTimestamp(2, new java.sql.Timestamp(myDate.getTime()));
			ps.setString(3, rc.getGender());
			ps.setLong(4, rc.getPhoneNumber());
			ps.setString(5, rc.getAddress());
			ps.setDouble(6, rc.getSalary());
			ps.execute();
//			Connector.a.ExecuteStatement(
//					"Insert into Employees (NameEM,Date_Of_Birth,Gender,Phone_Number, Address, Salary) values("
//							+ rc.getName() + ",'" + rc.getDateBirth() + "','" + rc.getGender()
//							+ ",'" + rc.getPhoneNumber() + "','" + rc.getAddress() + "','" + rc.getSalary() + "')");
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
			System.out.println("update  Employees set NameEM = '" + name + "' where EM_ID = " + ID_num);
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update  Employees set NameEM = '" + name + "' where EM_ID = " + ID_num + ";");
			Connector.a.connectDB().close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void updatePhone_num(int ID_num, long phone_num) {

		try {
			System.out.println("update  Employees set Phone_Number = " + phone_num + " where EM_ID = " + ID_num);
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update  Employees set Phone_Number = " + phone_num + " where  EM_ID = " + ID_num + ";");
			Connector.a.connectDB().close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		refresh();

	}

	public void updateBDate(int ID_num, String date) {
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
			System.out.println("update  Employees set Date_Of_Birth = " + sqlDate + " where EM_ID = " + ID_num);
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update  Employees set Date_Of_Birth = " + sqlDate + " where EM_ID = " + ID_num + ";");
			Connector.a.connectDB().close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void updateGender(int ID_num, char gender) {
		try {
			System.out.println("update  Employees set Gender = " + gender + " where EM_ID = " + ID_num);
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update  Employees set Gender = " + gender + " where  EM_ID= " + ID_num + ";");
			Connector.a.connectDB().close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void updateAddress(int ID_num, String address) {
		try {
			System.out.println("update  Employees set Address = " + address + " where EM_ID = " + ID_num);
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update  Employees set Address = " + address + " where  EM_ID= " + ID_num + ";");
			Connector.a.connectDB().close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void updateSalary(int ID_num, double salary) {
		try {
			System.out.println("update  Employees set Salary = " + salary + " where EM_ID = " + ID_num);
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update  Employees set Salary = " + salary + " where  EM_ID= " + ID_num + ";");
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
			System.out.println("delete from  Employees where ID_num ="+id + ";");
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("delete from  Employees where EM_ID ="+id + ";");
			Connector.a.connectDB().close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void getData() {
		String SQL = "select * from Employees";
		try {
			Connector.a.connectDB();
			Statement state = Connector.a.connectDB().createStatement();
			ResultSet rs = state.executeQuery(SQL);
			while(rs.next()) {
				EmployeeData em = new EmployeeData(rs.getDate(2).toString(), rs.getString(3), rs.getString(4), rs.getString(5),Long.parseLong(rs.getString(6)), Double.parseDouble(rs.getString(7)));
				em.setID(rs.getInt(1));
				dataList.add(em);
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
