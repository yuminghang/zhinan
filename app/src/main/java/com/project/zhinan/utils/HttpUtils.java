package com.project.zhinan.utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by ymh on 2016/4/11.
 */
public class HttpUtils {
    public static final int DATA_GET = 2;
    private static OkHttpClient client;
    private static String resString;

    public static void getData(String url, final Handler handler) {
        client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                //NOT UI Thread
                if (response.isSuccessful()) {
                    String resString = response.body().string();

                    Message msg = new Message();
                    Bundle b = new Bundle();
                    b.putString("content", resString);
                    msg.setData(b);
                    msg.what=DATA_GET;
                    handler.sendMessage(msg);
                }
            }
        });
    }


}
