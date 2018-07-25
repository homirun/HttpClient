package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * optionのListに入れる値を定めたクラス
 */
public class OptionListModel {
    private StringProperty header = new SimpleStringProperty();;
    private StringProperty value = new SimpleStringProperty();;

    /**
     * コンストラクタ
     * @param header header名
     * @param value headerの値
     */
    public OptionListModel(String header,String value){
        this.header.set(header);
        this.value.set(value);
    }

    /**
     * header名を返す
     * @return header名
     */
    public StringProperty getHeader(){
        return header;
    }

    /**
     * headerの値を返す
     * @return headerの値
     */
    public StringProperty getHeaderValue(){
        return value;
    }
}
