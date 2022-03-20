import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;

public class clientController {
	
	Alert alert = new Alert(Alert.AlertType.ERROR);
	
	private String computerName = "";
	
	private String port = "";
	
	private String serverPort = "7777";
	
	private Client c;
	
	@FXML
    private TextField textField;

    @FXML
    private TextField computerNameField;

    @FXML
    private TextField portNumber;

    @FXML
    private Button connect;

    @FXML
    private Button disConnect;

    @FXML
    private TextArea textArea;
    
    @FXML
    void connectPressed(ActionEvent event) {
    	Socket s = null;
    	try {
			s = new Socket("127.0.0.1", 7777);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	computerName = computerNameField.getText();
    	port = portNumber.getText();
    	
    	computerNameField.clear();
    	portNumber.clear();
    	
		if(port.matches(serverPort)) {
			Data d = new Data(computerName + " : logging in . . .");
			Client c = new Client(computerName, port, d);    	
	    	new ClientThread(s ,this, "127.0.0.1" , c, d).start();
		}
		else {
			notification();
		}
    }

    @FXML
    void disConnectPressed(ActionEvent event) {
    	textField.setDisable(true);
    }
        
    public void notification() {
    	alert.setTitle("log in");
    	alert.setHeaderText("error : wrong port.");
    	alert.setContentText("please connect again to the server ");
    	alert.showAndWait();
    }

	public TextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(TextArea textArea) {
		this.textArea = textArea;
	}

	public TextField getTextField() {
		return textField;
	}

	public void setTextField(TextField textField) {
		this.textField = textField;
	}

	public Client getC() {
		return c;
	}

	public void setC(Client c) {
		this.c = c;
	}
}
