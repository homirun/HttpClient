package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Label urlLabel;
    @FXML
    private TextField urlField;
    @FXML
    private TextArea headerArea;


    // submit
    @FXML
    public void onSubmitClick(ActionEvent evt) {
        //TODO: 例外処理
        try {
            HttpConnection httpConnection = new HttpConnection(urlField.getText());
            headerArea.setText(httpConnection.sendRequest());
            System.out.println("submit");
        }catch (IOException e){
            // TODO: GUIにエラーを表示させる
            System.out.println(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}