package com.sf.oarage.pentakillclient.network;


import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * 带授权认证的POST请求
 * Created by spawn on 17-10-24.
 */

public class RestAuthPostRequester extends RestPostRequester {


    public RestAuthPostRequester(String url) {
        super(url);
    }

    @Override
    public void execute(String json, Callback t) {
        super.execute(json, t);
    }

    @Override
    public void onRequest(String requestParams, HttpURLConnection urlConnection) throws IOException {
        super.onRequest(requestParams, urlConnection);
    }
}
