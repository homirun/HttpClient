package sample;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
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
    public Response sendRequest(String method) throws IOException {

        // GET
        Request req;
        req = new Request.Builder().url(url).get().build();
        Response res = client.newCall(req).execute();
        return res;
    }

    public Response sendRequest(String method, RequestBody body) throws IOException{

        Request req;

        //POST PUT
        if(method.equals("POST")) {
            req = new Request.Builder().url(url).post(body).build();
        }
        else{
            req = new Request.Builder().url(url).put(body).build();
        }

        Response res = client.newCall(req).execute();
        return res;
    }

}
