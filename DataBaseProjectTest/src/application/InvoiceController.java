package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;

public class InvoiceController {

	public static InvoiceData data;

	@FXML
	private TableView<InvoiceData> TableData;

	@FXML
	private TableColumn<InvoiceData, Integer> OrderIDColumn;

	@FXML
	private TableColumn<InvoiceData, Double> TotalpaymentDisscountcolumn;

	@FXML
	private Button Close;

	@FXML
	private Button Print;

	@FXML
	void InvoiceBackOnAction(ActionEvent event) {

	}

	@FXML
	void PrintOnAction(ActionEvent event) {

	}

}
