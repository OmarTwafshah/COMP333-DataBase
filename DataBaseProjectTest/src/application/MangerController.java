package application;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

public class MangerController {

	@FXML
	private Button custBack;

	@FXML
	private TextArea MangerID;

	@FXML
	private TextArea UserName;

	@FXML
	private TextArea DateBirth;

	@FXML
	private TextArea Address;

	@FXML
	private TextArea FirstName;

	@FXML
	private TextArea LastName;

	@FXML
	private TextArea Gender;

	@FXML
	private TextArea PhoneNumber;

	@FXML
	public void initialize() throws ClassNotFoundException {
		String query = "select UsersID, userName , phone , birthDate, address, first , last  , gender from Users Where UsersID = "
				+ controller.UsersID + ";";
		try (Statement stmt = Connector.a.connectDB().createStatement()) {
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				MangerID.setText(rs.getString("UsersID"));
				UserName.setText(rs.getString("userName"));
				PhoneNumber.setText(rs.getString("phone"));
				DateBirth.setText(rs.getString("birthDate"));
				Address.setText(rs.getString("address"));
				FirstName.setText(rs.getString("first"));
				LastName.setText(rs.getString("last"));
				Gender.setText(rs.getString("gender"));
			}
		} catch (SQLException e) {
			System.out.println("Error ");
		}
	}

	@FXML
	void BackOnAction(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage thisStage = (Stage) node.getScene().getWindow();
		thisStage.close();
	}

}
