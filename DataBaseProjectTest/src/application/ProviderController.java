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

public class ProviderController {

	private Stage stage;
	private Scene scene;
	private Parent root;

	private ArrayList<ProviderData> data;
	private ObservableList<ProviderData> dataList;

	

	@FXML
	private TableView<ProviderData> TableData;

	@FXML
	private TableColumn<ProviderData, Integer> IDColumn;

	@FXML
	private TableColumn<ProviderData, String> NameColumn;

	@FXML
	private TableColumn<ProviderData, String> DatePColumn;


	@FXML
	private TableColumn<ProviderData, Double> ProviderPriceColumn;

	@FXML
	private Button Update;

	@FXML
	private TextField oldID;

	@FXML
	private TextField newName;

	@FXML
	private TextField newDateP;

	

	@FXML
	private TextField newProviderPrice;

	@FXML
	private TextField SearchFiled;

	@FXML
	private TextField addID;

	@FXML
	private TextField addName;

	@FXML
	private TextField addDate;

	

	@FXML
	private TextField addProviderPrice;

	@FXML
	private Button Delete;

	@FXML
	private Button Insert;

	@FXML
	private Button providerBack;

	@FXML
	private TextField ID;

	@FXML
	public void initialize() {
		data = new ArrayList<>();
		dataList = FXCollections.observableArrayList(data);
		TableData.setEditable(true);

		IDColumn.setCellFactory(TextFieldTableCell.<ProviderData, Integer>forTableColumn(new IntegerStringConverter()));
		IDColumn.setCellValueFactory(new PropertyValueFactory<ProviderData, Integer>("Provider_ID"));

		NameColumn.setCellValueFactory(new PropertyValueFactory<ProviderData, String>("Provider_Name"));
		NameColumn.setCellFactory(TextFieldTableCell.<ProviderData>forTableColumn());
		
		DatePColumn.setCellValueFactory(new PropertyValueFactory<ProviderData, String>("DateP"));
		DatePColumn.setCellFactory(TextFieldTableCell.<ProviderData>forTableColumn());
		
		ProviderPriceColumn.setCellFactory(TextFieldTableCell.<ProviderData, Double>forTableColumn(new DoubleStringConverter()));
		ProviderPriceColumn.setCellValueFactory(new PropertyValueFactory<ProviderData, Double>("Provider_Price"));
		

	
		
		getData();
		TableData.setItems(dataList);
	}
	@FXML
	void providerBackOnAction(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("AfterLogin.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void DeleteSetAction(ActionEvent event) {

		try {
			if (ID.getText() != null) {
				int id = Integer.parseInt(ID.getText());
				deleteRow(id);
				refresh();
			}
		} catch (Exception e) {

		}
		

	}

	@FXML
	void UpdateSetAction(ActionEvent event) {
		try {
			if (oldID.getText() != null) {
				int id = Integer.parseInt(oldID.getText());
				if (newName.getText().length() > 0) {
					System.out.println(newName.getText());
					updateName(id, newName.getText());
				}
				if (newDateP.getText().length() > 0) {
					System.out.println(newDateP.getText());
					updateBDate(id, newDateP.getText());
				}
				
				if (newProviderPrice.getText().length() > 0) {
					double salary = Double.parseDouble(newProviderPrice.getText());
					updateProviderPrice(id, salary);
				}
				refresh();
			}
		} catch (Exception e) {

		}
	}

	@FXML
	void addSetAction(ActionEvent event) {

		ProviderData rc;
		rc = new ProviderData(addName.getText(), Double.parseDouble(addProviderPrice.getText()),
				addDate.getText());
		dataList.add(rc);
		insertData(rc);
		addName.clear();
		addProviderPrice.clear();
		addDate.clear();
		refresh();
	}

	private void insertData(ProviderData rc) {

		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			Date myDate = new Date();
			java.sql.Date sqlDate;
			try {
				myDate = formatter.parse(rc.getDateP());

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sqlDate = new java.sql.Date(myDate.getTime());

			System.out.println("Insert into Provider (Provider_Name,Provider_Price,DateP) values('" + rc.getProvider_Name()+ "','"
					+ rc.getProvider_Price() + "','" + sqlDate + "')");

			Connector.a.connectDB();
			Connector.a.ExecuteStatement(
					"Insert into Provider (Provider_Name,Provider_Price,DateP) values('" + rc.getProvider_Name()+ "','"
							+ rc.getProvider_Price() + "','" + sqlDate + "')");
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
			System.out.println("delete from  Provider where Provider_ID =" + id + ";");
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("delete from  Provider where Provider_ID =" + id + ";");
			Connector.a.connectDB().close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void updateName(int Provider_ID, String name) {

		try {
			System.out
					.println("update  Provider set Provider_Name = '" + name + "' where Provider_ID = " + Provider_ID);
			Connector.a.connectDB();
			Connector.a.ExecuteStatement(
					"update  Provider set Provider_Name = '" + name + "' where Provider_ID = " + Provider_ID + ";");
			Connector.a.connectDB().close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	public void updateBDate(int Provider_ID, String date) {
	
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
			
			Connector.a.connectDB();
			sqlDate = new java.sql.Date(myDate.getTime());
			String sql = ("update  Provider set DateP = ? where Provider_ID = ?");
			PreparedStatement pstmt = Connector.a.connectDB().prepareStatement(sql);
			pstmt.setTimestamp(1, new java.sql.Timestamp(myDate.getTime()));
			pstmt.setInt(2, Provider_ID);
			pstmt.executeUpdate();
			pstmt.close();
			Connector.a.connectDB().close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateProviderPrice(int Provider_ID, double Provider_Price) {
		try {
			System.out.println(
					"update  Provider set Provider_Price = " + Provider_Price + " where Provider_ID = " + Provider_ID);
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update  Provider set Provider_Price = " + Provider_Price
					+ " where  Provider_ID= " + Provider_ID + ";");
			Connector.a.connectDB().close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void getData() {
		String SQL = "select * from Provider";
		try {
			Connector.a.connectDB();
			Statement state = Connector.a.connectDB().createStatement();
			ResultSet rs = state.executeQuery(SQL);
			while(rs.next()) {
				dataList.add(new ProviderData(rs.getInt(1), rs.getString(2), rs.getDouble(3),rs.getString(4)));
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