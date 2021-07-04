package application;

import java.io.IOException;

import com.sun.glass.ui.Menu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

public class AfterLogin {

	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private ComboBox<String> menu;

	@FXML
	private Button employee;

	@FXML
    private Button customer;
	
	@FXML
	private Button items;

	@FXML
	private Button provider;

	@FXML
	private Button department;

	@FXML
	private Button warehouses;

	
	@FXML
	void employeeOnAction(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("Employee.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stage = new Stage();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	 @FXML
	    void customerOnAction(ActionEvent event) {
		 try {
				root = FXMLLoader.load(getClass().getResource("Customer.fxml"));
				stage = new Stage();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	    }
	@FXML
	void itemsOnAction(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("Items.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stage = new Stage();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	

	@FXML
	void providerOnAction(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("Provider.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stage = new Stage();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void warehousesOnAction(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("Store.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stage = new Stage();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	@FXML
	public void initialize() {
		ObservableList<String> list = FXCollections.observableArrayList();
		list.addAll("User Settings", "Log Out");
		Image image1 = new Image("file:\\C:\\Users\\Redmi\\Desktop/UntitledDesign-19.png");
		BackgroundSize bSize = new BackgroundSize(1.0, 1.0, true, true, false, false);
		menu.setBackground(new Background(new BackgroundImage(image1, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bSize)));

		menu.setItems(list);
	}

	@FXML
	void depOnAction(ActionEvent event) {

		try {
			root = FXMLLoader.load(getClass().getResource("Department.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stage = new Stage();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}
	
	@FXML
	void menuOnAction(ActionEvent event) {
		if (menu.getSelectionModel().getSelectedItem() == "User Settings") {

			try {
				root = FXMLLoader.load(getClass().getResource("MangerInfo.fxml"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stage = new Stage();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		} else if (menu.getSelectionModel().getSelectedItem() == "Log Out") {
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
	}


}
