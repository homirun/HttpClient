package sample;

import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


/**
 * optionのListCellのUIを記述したクラス
 */
public class OptionCell extends ListCell<OptionListModel> {
    private HBox hbox = new HBox(10);
    private Text txtHeader = new Text();
    private Text txtValue = new Text();
    private Button delButton = new Button();

    /**
     * コンストラクタ
     */
    public OptionCell(){
        initComponent();
        initStyle();
    }

    /**
     * Styleの初期化
     */
    public void initStyle(){
        txtHeader.setFont(new Font("System Bold", 18.0));
        txtValue.setFont(new Font(18.0));
    }

    /**
     * コンポーネントの初期化
     */
    public void initComponent(){
        HBox.setHgrow(txtHeader, Priority.NEVER);
        HBox.setHgrow(txtValue, Priority.NEVER);
        HBox.setHgrow(delButton, Priority.NEVER);
        delButton.setOnAction(event -> getListView().getItems().remove(getItem()));
        hbox.getChildren().addAll(txtHeader,txtValue,delButton);

    }

    /**
     * 指定しているObservedListが更新されたときの処理
     * @param item　ObservedListの値
     * @param empty 変更された値が空かどうか
     */
    @Override
    protected void updateItem(OptionListModel item, boolean empty) {
        super.updateItem(item, empty);
        if(empty){
            setText(null);
            setGraphic(null);
        }else{
            txtHeader.setText(item.getHeader().get());
            txtValue.setText(item.getHeaderValue().get());
            delButton.setText("X");
            setGraphic(hbox);
        }
    }
}
