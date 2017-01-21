package com.project.zhinan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.zhinan.MyApplication;
import com.project.zhinan.R;
import com.project.zhinan.api.Urls;
import com.project.zhinan.bean.bean_version2;
import com.project.zhinan.utils.DensityUtils;
import com.project.zhinan.utils.HttpUtils;

public class FlashActivity extends Activity {

    private static boolean result = false;
    private bean_version2 datas;
    private Runnable runnable;
    private LinearLayout linearLayout;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case HttpUtils.DATA_GET:
                    Gson gson = new Gson();
                    try {
                        datas = (gson.fromJson(msg.getData().getString("content"), new TypeToken<bean_version2>() {
                        }.getType()));
                        initImg(datas.getData().get(0).getImgurls());
                    } catch (Exception e) {

                    }
                    break;
            }
        }
    };

    public void initImg(String[] img_urls) {
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                DensityUtils.getHeight(FlashActivity.this) - DensityUtils.dp2px(FlashActivity.this, 50));

        ImageView iv = new ImageView(this);
        iv.setLayoutParams(vlp);//设置TextView的布局
        iv.setPadding(0, 0, 0, 0);//设置边距
        linearLayout.addView(iv);//将TextView 添加到子View 中
        Glide.with(FlashActivity.this).load(img_urls[0]).into(iv);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        MyApplication.getInstance().addActivity(this);
        setContentView(R.layout.flashactivity);
        linearLayout = (LinearLayout) findViewById(R.id.container);
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
                HttpUtils.getData(Urls.Jiaoyu, handler);
            }
        }.start();
    }

    private void task() {
        startActivity(new Intent(FlashActivity.this, MainActivity.class));
    }

    public void click(View view) {
        handler.removeCallbacks(runnable);
        task();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK
//                && event.getAction() == KeyEvent.ACTION_DOWN) {
//            MyApplication.getInstance().exit();
//        }
        return super.onKeyDown(keyCode, event);
    }
}
