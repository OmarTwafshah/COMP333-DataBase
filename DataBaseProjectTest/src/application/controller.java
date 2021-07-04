package application;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;

public class controller {

	private Stage stage;
	private Scene scene;
	private Parent root;
	public static int UsersID;
	
	@FXML
	private ToggleButton signup;

	@FXML
	private ToggleGroup toggle;

	@FXML
	private ToggleButton signin;

	@FXML
	private HBox usernameHbox;

	@FXML
	private TextField username;

	@FXML
	private HBox passwordHbox;

	@FXML
	private TextField password;

	@FXML
	private HBox jobHbox;

	@FXML
	private PasswordField passwordF;

	@FXML
	private RadioButton employee;

	@FXML
	private ToggleGroup jgroup;

	@FXML
	private RadioButton manager;

	@FXML
	private HBox birthHbox;

	@FXML
	private ComboBox<Integer> day;

	@FXML
	private ComboBox<Integer> month;

	@FXML
	private ComboBox<Integer> year;

	@FXML
	private HBox addressHbox;

	@FXML
	private TextField address;

	@FXML
	private HBox firstnameHbox;

	@FXML
	private TextField firstname;

	@FXML
	private HBox lastnameHbox;

	@FXML
	private TextField lastname;

	@FXML
	private HBox phoneHbox;

	@FXML
	private TextField phonenumber;

	@FXML
	private HBox genderHbox;

	@FXML
	private RadioButton male;

	@FXML
	private ToggleGroup gender;

	@FXML
	private RadioButton female;

	@FXML
	private Button sign;

	@FXML
	private VBox invbox;

	@FXML
	void initialize() {
		for (int i = 1; i <= 30; i++) {
			day.getItems().add(i);
		}
		for (int i = 2021; i > 1940; i--) {
			year.getItems().add(i);
		}
		for (int i = 1; i <= 12; i++) {
			month.getItems().add(i);
		}
	}

	@FXML
	void SignupAction(ActionEvent event) {
		toggle.selectedToggleProperty().addListener((obsVal, oldVal, newVal) -> {
			if (newVal == null)
				oldVal.setSelected(true);
		});

		usernameHbox.setVisible(true);
		passwordHbox.setVisible(true);
		jobHbox.setVisible(true);
		birthHbox.setVisible(true);
		addressHbox.setVisible(true);
		firstnameHbox.setVisible(true);
		lastnameHbox.setVisible(true);
		phoneHbox.setVisible(true);
		genderHbox.setVisible(true);
		if (username != null) {
			username.clear();
		}

		if (password != null) {
			password.setText("");
		}
		sign.setText("Sign Up");
		sign.setVisible(true);
	}

	@FXML
	void signinAction(ActionEvent event) {


		toggle.selectedToggleProperty().addListener((obsVal, oldVal, newVal) -> {
			if (newVal == null)
				oldVal.setSelected(true);
		});

		birthHbox.setVisible(false);
		addressHbox.setVisible(false);
		firstnameHbox.setVisible(false);
		lastnameHbox.setVisible(false);
		phoneHbox.setVisible(false);
		genderHbox.setVisible(false);
		usernameHbox.setVisible(true);
		if (username != null) {
			username.clear();
		}
		passwordHbox.setVisible(true);
		if (password != null) {
			password.setText("");
		}
		jobHbox.setVisible(false);
		sign.setText("Sign In");
		sign.setVisible(true);
		username.setText("");
	}

	@FXML
	void signInButtonAction(ActionEvent event) {

		if (toggle.getSelectedToggle() == signin) {
			try {
				PreparedStatement st = Connector.a.connectDB()
						.prepareStatement("select * from Users where username = ? AND password = ? ");
				st.setString(1, username.getText());
				st.setString(2, passwordF.getText());
				ResultSet r1 = st.executeQuery();
				if (r1.next()) {
					UsersID = r1.getInt(1);
					if (r1.getString(4).equals("m")) {
						try {
							root = FXMLLoader.load(getClass().getResource("afterLogin.fxml"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
						scene = new Scene(root);
						stage.setScene(scene);
						stage.show();
					}else if(r1.getString(4).equalsIgnoreCase("e")) {
						try {
							root = FXMLLoader.load(getClass().getResource("cashier.fxml"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
						scene = new Scene(root);
						stage.setScene(scene);
						stage.show();
					}
				}

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (toggle.getSelectedToggle() == signup) {
			if (username.getText() != null && passwordF.getText() != null && day.getValue() != null
					&& month.getValue() != null && year.getValue() != null && address.getText() != null
					&& firstname.getText() != null && lastname.getText() != null) {
				try {
					Connector.a.connectDB();
					String selection = "";
					if (jgroup.getSelectedToggle() == employee) {
						selection = "e";
					} else {
						selection = "m";
					}
					String date = year.getValue() + "-" + month.getValue() + "-" + day.getValue();
					DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

					Date myDate = new Date();
					try {
						myDate = formatter.parse(date);

					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					RadioButton s1 = (RadioButton) gender.getSelectedToggle();
//					Connector.a.ExecuteStatement("Insert into `Users` (userName,password,status,birthDate,address,first,last,phone,gender) values(" + username.getText() + ",'"
//							+ passwordF.getText()+ ",'"+ selection+ ",'"+ sqlDate+ ",'" + address.getText()+ ",'" +firstname.getText()+ ",'" +lastname.getText()+ ",'" +Long.parseLong(phonenumber.getText())+ ",'" +s1.getText()+ "')");
					String sql = "Insert into Users (userName,password,status,birthDate,address,first,last,phone,gender) values(?,?,?,?,?,?,?,?,?)";
					PreparedStatement st = Connector.a.connectDB().prepareStatement(sql);
					st.setString(1, username.getText());
					st.setString(2, passwordF.getText());
					st.setString(3, selection);
					st.setTimestamp(4, new java.sql.Timestamp(myDate.getTime()));
					st.setString(5, address.getText());
					st.setString(6, firstname.getText());
					st.setString(7, lastname.getText());
					st.setLong(8, Long.parseLong(phonenumber.getText()));
					st.setString(9, s1.getText());
					st.execute();
					PreparedStatement jb = Connector.a.connectDB().prepareStatement("select * from Users where username = ? AND password = ? ");
					jb.setString(1, username.getText());
					jb.setString(2, passwordF.getText());
					ResultSet r1 = jb.executeQuery();
					if (r1.next()) {
						UsersID = r1.getInt(1);
					}
					st.close();
					Connector.a.connectDB().close();
					st.close();
					Connector.a.connectDB().close();

					if (jgroup.getSelectedToggle() == manager) {
						
						try {
							root = FXMLLoader.load(getClass().getResource("afterLogin.fxml"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
						scene = new Scene(root);
						stage.setScene(scene);
						stage.show();
					}else if(jgroup.getSelectedToggle() == employee) {
						try {
							root = FXMLLoader.load(getClass().getResource("cashier.fxml"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
						scene = new Scene(root);
						stage.setScene(scene);
						stage.show();
					}

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}