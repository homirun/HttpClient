package sample;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HttpConnection {
    private OkHttpClient client = new OkHttpClient();
    private String url;

    public HttpConnection(String url) {
        this.url = url;
    }

    //TODO: メソッドはenumで
    public String sendRequest() throws IOException {
        Request req = new Request.Builder().url(url).build();

        Response res = client.newCall(req).execute();
        return res.headers().toString();
    }
}
