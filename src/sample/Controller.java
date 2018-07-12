package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
        try {
            HttpConnection httpConnection = new HttpConnection(urlField.getText());
            headerArea.setText(httpConnection.sendRequest());

            urlField.setStyle("");

            System.out.println("submit");
        }catch (IOException e){
            // TODO: GUIにエラーを表示させる

            System.out.println(e);
        }catch(IllegalArgumentException e){  // urlが不正
            urlField.setStyle("-fx-base: #FF0000");
        }
    }

    // option
    @FXML
    public void onOptionClick(ActionEvent evt) throws IOException{
        // optionStageを宣言
        Stage optionStage = new Stage();
        // option.fxmlをロードしたWindowを作成
        Parent root = FXMLLoader.load(getClass().getResource("option.fxml"));
        optionStage.initModality(Modality.WINDOW_MODAL);
        optionStage.initOwner(((Node)evt.getSource()).getScene().getWindow());
        optionStage.setScene(new Scene(root));
        optionStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}