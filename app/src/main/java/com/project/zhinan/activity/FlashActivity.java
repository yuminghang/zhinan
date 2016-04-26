package com.project.zhinan.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.project.zhinan.MyApplication;
import com.project.zhinan.R;
import com.project.zhinan.utils.ConstantValue;
import com.project.zhinan.utils.ToolFor9Ge;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class FlashActivity extends Activity {

    private static boolean result = false;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//            startActivity(new Intent(FlashActivity.this, MainActivity.class));
        }
    };
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        MyApplication.getInstance().addActivity(this);
        setContentView(R.layout.flashactivity);
        checkServer();
        runnable = new Runnable() {
            @Override
            public void run() {
                task();
            }
        };
        handler.postDelayed(runnable, 5000);
    }

    private void checkServer() {
        new Thread() {
            @Override
            public void run() {
                postJson();
            }
        }.start();
    }

    private void postJson() {
        //申明给服务端传递一个json串
        //创建一个OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();

        //创建一个请求对象
        Request request = new Request.Builder()
                .url(ConstantValue.CheckUrl)
                .build();
        //发送请求获取响应
        try {
            Response response = okHttpClient.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                //打印服务端返回结果
                String re = response.body().string();
                if (re.contains("success")) {
                    result = true;
                } else {
                    result = false;
                }
            } else {
                result = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void task() {
        if (result) {
            startActivity(new Intent(FlashActivity.this, MainActivity.class));
        } else {
            if (!ToolFor9Ge.checkNetworkInfo(FlashActivity.this)) {
                startActivity(new Intent(FlashActivity.this, MainActivity.class));
            } else {
                Toast.makeText(FlashActivity.this, "手机或服务器网络连接失败，请联系开发人员！", Toast.LENGTH_SHORT).show();
                MyApplication.getInstance().exit();
            }
        }
    }

    public void click(View view) {
        handler.removeCallbacks(runnable);
        task();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            MyApplication.getInstance().exit();
        }
        return super.onKeyDown(keyCode, event);
    }
}
