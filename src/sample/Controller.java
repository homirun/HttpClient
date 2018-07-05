package sample;

import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.security.PrivateKey;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Label urlLabel;
    @FXML
    private TextField submit;

    @FXML
    public void load(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        urlLabel.setText("hoge");
    }
}
