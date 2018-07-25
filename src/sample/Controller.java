package sample;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.NestedParticle;
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
import okhttp3.*;

import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 *　メインウィンドウの内部ロジックを記述しているクラス
 */
public class Controller implements Initializable {

    @FXML
    private TextField urlField;
    @FXML
    private TextArea responseArea;
    @FXML
    private ListView<String> HeaderDetailListView;
    @FXML
    private ChoiceBox<String> methodBox;
    @FXML
    private ListView<OptionListModel> optionHeaderListView;
    @FXML
    private TextField requestHeaderField;
    @FXML
    private TextField requestHeaderValueField;
    @FXML
    private Button requestHeaderAdd;



    private ObservableList<String> items = FXCollections.observableArrayList();

    private String method = "GET";
    private Map<String,String> reqHeaders = new LinkedHashMap<>();
    private ObservableList<OptionListModel> requestHeaderModels = FXCollections.observableArrayList();

    /**
     * submitを押したときに発火するイベントハンドラ
     * @param evt ActionEvent Obj
     */
    @FXML
    public void onSubmitClick(ActionEvent evt) {

        if(items != null){
            items.clear();
        }

        try {
            String url = urlField.getText();
            if(!url.contains("http://") && !url.contains("https://")){
               url = "http://" + url;
            }
            HttpConnection httpConnection = new HttpConnection(url);
            Response res;
            RequestBody reqBody;
            method = methodBox.getValue();

            if(method.equals("GET")) {
                System.out.println("GET");
                res = httpConnection.sendRequest(method, reqHeaders);
            }else{
                System.out.println(method);
                //TODO: RequestBodyを変数に置き換える
                reqBody= RequestBody.create(MediaType.parse("text/plain;charset=utf-8"),"");
                res = httpConnection.sendRequest(method, reqBody, reqHeaders);
            }

            responseArea.setText(res.body().string());
            for(int i = 0; i < res.headers().size(); i++){
                items.add(res.headers().name(i) + ": " + res.headers().value(i));
            }
            urlField.setStyle("");

            System.out.println("submit");
        }catch(SSLHandshakeException e){
            System.out.println(e);  // SSL証明書関連での例外　ワイルドカード証明書だと怒られる
        }catch(IllegalArgumentException | IOException e){  // urlが不正
            System.out.println(e);
            urlField.setStyle("-fx-base: #FF0000");
        }
    }

    @FXML
    public void onRequestHeaderAddClick(){
        reqHeaders.put(requestHeaderField.getText(), requestHeaderValueField.getText());
        requestHeaderModels.add(new OptionListModel(requestHeaderField.getText(), requestHeaderValueField.getText()));
    }

    /**
     * optionを押したときに発火するイベントハンドラ
     * @param evt ActionEvent Obj
     * @throws IOException option.fxmlが見当たらないとき
     */
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

    /**
     * init
     * @param location スーパークラスで使用
     * @param resources スーパークラスで使用
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // ListViewにObservableなリストを設定する。
        HeaderDetailListView.setItems(items);

        //optionHeaderListViewへOptionCellの適用。
        optionHeaderListView.setCellFactory(param -> new OptionCell());
        optionHeaderListView.setItems(requestHeaderModels);

        // MethodListにObservableなリストを設定する。
        ObservableList<String> methodList = FXCollections.observableArrayList();
        methodList.add("GET");
        methodList.add("POST");
        methodList.add("PUSH");
        methodBox.setItems(methodList);
        methodBox.getSelectionModel().selectFirst();

    }
}