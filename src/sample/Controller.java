package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import okhttp3.Headers;
import okhttp3.Response;

import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Label urlLabel;
    @FXML
    private TextField urlField;
    @FXML
    private TextArea headerArea;
    @FXML
    private ListView<String> HeaderDetailListView;

    private ObservableList<String> items = FXCollections.observableArrayList();


    // submit
    @FXML
    public void onSubmitClick(ActionEvent evt) {
        try {
            HttpConnection httpConnection = new HttpConnection(urlField.getText());
            Response res = httpConnection.sendRequest();
            headerArea.setText(res.headers().toString());
            for(int i = 0; i < res.headers().size(); i++){
                items.add(res.headers().name(i) + ": " + res.headers().value(i));
            }

            urlField.setStyle("");

            System.out.println("submit");
        }catch(SSLHandshakeException e){
            System.out.println(e);
        }catch(IllegalArgumentException | IOException e){  // urlが不正
            System.out.println(e);
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
        // ListViewにObservableなリストを設定する。
        HeaderDetailListView.setItems(items);
    }
}