package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import okhttp3.*;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.net.URL;
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
    private TextArea responseBodyArea;


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
            
            if(reqHeaders.size() != requestHeaderModels.size()){
                reqHeaders.clear();
                for(OptionListModel obj: requestHeaderModels){

                    reqHeaders.put(obj.getHeader().toString(), obj.getHeaderValue().toString());
                }
            }

            if(method.equals("GET")) {
                System.out.println("GET");
                res = httpConnection.sendRequest(method, reqHeaders);
            }else{
                System.out.println(method);
                reqBody= RequestBody.create(MediaType.parse("text/plain;charset=utf-8"), responseBodyArea.getText());
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

    /**
     * RequestHeaderタブのAddボタンを押したときに発火するイベントハンドラ
     */
    @FXML
    public void onRequestHeaderAddClick(){
        reqHeaders.put(requestHeaderField.getText(), requestHeaderValueField.getText());
        requestHeaderModels.add(new OptionListModel(requestHeaderField.getText(), requestHeaderValueField.getText()));
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