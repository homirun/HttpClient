package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * メインウィンドウの初期化とエンドポイントを記述したクラス。
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // fxmlのロード
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("HTTP Client");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.show();
    }

    /**
     * エントリーポイント
     * @param args コマンドライン引数
     */
    public static void main(String[] args) {
        launch(args);
    }
}
