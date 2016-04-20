package com.project.zhinan.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.project.zhinan.MyApplication;
import com.project.zhinan.R;
import com.project.zhinan.base.fragment.BaseFragment;
import com.project.zhinan.fragment.FabuFragment;
import com.project.zhinan.fragment.FaxianFragment;
import com.project.zhinan.fragment.HomeFragment;
import com.project.zhinan.fragment.SettingFragment;
import com.project.zhinan.utils.StatusBarUtil;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;


public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private long exitTime;
    private static final int POSTED = 1;
    FrameLayout fragment_container;
    HomeFragment homeFragment;
    SettingFragment settingFragment;
    FaxianFragment faxianFragment;
    FabuFragment fabuFragment;
    RadioButton btn_home, btn_dongtu, btn_faxian, btn_setting;
    private ArrayList<String> myCollects;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case POSTED:
                    if (success.contains("success")) {
                        Toast.makeText(MainActivity.this, "收藏信息上传成功", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
        }
    };
    private String success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().addActivity(this);
        setContentView(R.layout.activity_main);
        fragment_container = (FrameLayout) findViewById(R.id.fragment_container);
        initRadioButton();
        select(0);
//        Log.e("tttt", getApplicationContext().getPackageResourcePath() + "/");
    }

    private void select(int i) {
        FragmentManager fm = getSupportFragmentManager();  //获得Fragment管理器
        FragmentTransaction ft = fm.beginTransaction(); //开启一个事务
        hideFragment(ft);   //先隐藏 Fragment

        switch (i) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    ft.replace(R.id.fragment_container, homeFragment);
                } else {
                    ft.show(homeFragment);
                }
                StatusBarUtil.initStatusBar(this, R.color.almost_white);

                break;
            case 1:
                if (fabuFragment == null) {
                    fabuFragment = new FabuFragment();
                    ft.add(R.id.fragment_container, fabuFragment);
                } else {
                    ft.show(fabuFragment);
                }
                StatusBarUtil.initStatusBar(this, R.color.almost_white);
                break;
            case 2:
                if (faxianFragment == null) {
                    faxianFragment = new FaxianFragment();
                    ft.add(R.id.fragment_container, faxianFragment);
                } else {
                    ft.show(faxianFragment);
                }
                StatusBarUtil.initStatusBar(this, R.color.almost_white);
                break;
            case 3:
                if (settingFragment == null) {
                    settingFragment = new SettingFragment();
                    ft.add(R.id.fragment_container, settingFragment);
                } else {
                    ft.show(settingFragment);
                }
                StatusBarUtil.initStatusBar(this, R.color.ivory);
                break;
        }
        ft.commit();   //提交事务
    }

    //隐藏所有Fragment
    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if (homeFragment != null) {
            fragmentTransaction.hide(homeFragment);
        }
        if (fabuFragment != null) {
            fragmentTransaction.hide(fabuFragment);
        }
        if (faxianFragment != null) {
            fragmentTransaction.hide(faxianFragment);
        }
        if (settingFragment != null) {
            fragmentTransaction.hide(settingFragment);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_home:
                select(0);
                break;
            case R.id.btn_dongtu:
                select(1);
                if (MyApplication.count > 0) {
                    sendCollectInfo();
                }
                break;
            case R.id.btn_faxian:
                select(2);
                if (MyApplication.count > 0) {
                    sendCollectInfo();
                }
                break;
            case R.id.btn_setting:
                select(3);
                if (MyApplication.count > 0) {
                    sendCollectInfo();
                }
                break;
        }
    }

    public static final MediaType TEXT
            = MediaType.parse("text/plain; charset=utf-8");

    private void sendCollectInfo() {
        new Thread() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("collect", Context.MODE_PRIVATE);
                Map<String, Integer> allContent = (HashMap<String, Integer>) sharedPreferences.getAll();
                StringBuilder sb = new StringBuilder();
                //注意遍历map的方法
                for (Map.Entry<String, Integer> entry : allContent.entrySet()) {
                    if (entry.getValue() == 1) {
                        sb.append(entry.getKey() + ",");
                    }
                }
                OkHttpClient okHttpClient = new OkHttpClient();

                //创建一个请求对象
                Request request = new Request.Builder()
                        .url("https://api.github.com/markdown/raw")
                        .post(RequestBody.create(TEXT, sb.toString()))
                        .build();
                //发送请求获取响应
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    //判断请求是否成功
                    if (response.isSuccessful()) {
                        //打印服务端返回结果
                        success = response.body().string();
                        Log.i("success", success);
                        handler.sendEmptyMessage(POSTED);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void initRadioButton() {
        btn_home = (RadioButton) findViewById(R.id.btn_home);
        btn_dongtu = (RadioButton) findViewById(R.id.btn_dongtu);
        btn_faxian = (RadioButton) findViewById(R.id.btn_faxian);
        btn_setting = (RadioButton) findViewById(R.id.btn_setting);
        btn_home.setOnClickListener(this);
        btn_dongtu.setOnClickListener(this);
        btn_faxian.setOnClickListener(this);
        btn_setting.setOnClickListener(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {

            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                MyApplication.getInstance().exit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}

