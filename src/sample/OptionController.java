package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *　オプションウィンドウの内部ロジックを記述しているクラス
 */
public class OptionController implements Initializable {

    @FXML
    ChoiceBox<String> methodBox;

    /**
     * init
     * @param location スーパークラスで使用
     * @param resources　スーパークラスで使用
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> methodList = FXCollections.observableArrayList();
        methodList.add("GET");
        methodList.add("POST");
        methodList.add("PUSH");

        methodBox.setItems(methodList);
    }
}
