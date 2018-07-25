package sample;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

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
     * GET Requestを送る
     * @param method HTTP method
     * @param reqHeaders request HeaderのMap
     * @return Responseオブジェクト
     * @throws IOException URLが不正だった場合スローされる
     */
    public Response sendRequest(String method, Map<String,String> reqHeaders) throws IOException {
        // GET
        Request req;
        Request.Builder builder = new Request.Builder();
        if(reqHeaders.size() > 0) {
            for (String key : reqHeaders.keySet()) {
                System.out.println(key);
                builder.addHeader(key, reqHeaders.get(key));
            }
        }

        req = builder.url(url).get().build();
        Response res = client.newCall(req).execute();
        return res;
    }


    /**
     * POST PUT Requestを送る
     * @param method HTTP method
     * @param body body
     * @param reqHeaders request HeaderのMap
     * @return Responseオブジェクト
     * @throws IOException URLが不正だった場合スローされる
     */
    public Response sendRequest(String method, RequestBody body, Map<String,String> reqHeaders) throws IOException{

        Request req;
        Request.Builder builder = new Request.Builder();
        //POST PUT
        if(method.equals("POST")) {
            if(reqHeaders.size() > 0){
                for (String key: reqHeaders.keySet()) {
                    builder.addHeader(key,reqHeaders.get(key));
                }
            }
            req = builder.url(url).post(body).build();
        }
        else{
            if(reqHeaders.size() > 0){
                for (String key: reqHeaders.keySet()) {
                    builder.addHeader(key,reqHeaders.get(key));
                }
            }
            req = builder.url(url).put(body).build();
        }

        Response res = client.newCall(req).execute();
        return res;
    }

}
