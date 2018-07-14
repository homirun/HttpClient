package sample;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * HTTPでの接続に関するクラス
 */
public class HttpConnection {
    private OkHttpClient client = new OkHttpClient();
    private String url;

    /**
     * コンストラクタ
     * @param url リクエストを送りたいurl
     */
    public HttpConnection(String url) {
        this.url = url;
    }

    //TODO: メソッドはenumで
    /**
     * Requestを送る
     * @return Responseオブジェクト
     * @throws IOException URLが不正だった場合スローされる
     */
    public Response sendRequest() throws IOException {
        Request req = new Request.Builder().url(url).build();

        Response res = client.newCall(req).execute();
        return res;
    }

}
