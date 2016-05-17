package com.project.zhinan.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
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
import com.jauker.widget.BadgeView;
import com.project.zhinan.MyApplication;
import com.project.zhinan.R;
import com.project.zhinan.api.Urls;
import com.project.zhinan.base.fragment.BaseFragment;
import com.project.zhinan.fragment.FabuFragment;
import com.project.zhinan.fragment.FaxianFragment;
import com.project.zhinan.fragment.HomeFragment;
import com.project.zhinan.fragment.SettingFragment;
import com.project.zhinan.utils.StatusBarUtil;
import com.project.zhinan.view.MyPopupWindow;
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
    RadioButton btn_home, btn_fabu, btn_faxian, btn_setting, btn_fabu_zhanwei;
    private ArrayList<String> myCollects;
    private int currentTab = 0;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case POSTED:
                    if (success.contains("success")) {
                        edit1.putInt("isUpload", 0);
                        edit1.commit();
                        MyApplication.count = 0;
                    }
                    break;
                default:
                    break;
            }
        }
    };
    private String success;
    private SharedPreferences sharedPreferences1;
    private SharedPreferences.Editor edit1;
//    private BadgeView badgeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().addActivity(this);
        setContentView(R.layout.activity_main);

        sharedPreferences1 = getSharedPreferences("collect_upload_state", Context.MODE_PRIVATE);
        edit1 = sharedPreferences1.edit();
        fragment_container = (FrameLayout) findViewById(R.id.fragment_container);
        initRadioButton();
//        initBadgeView();
        select(0);
    }

//    private void initBadgeView() {
//        badgeView = new BadgeView(this);
//        badgeView.setTargetView(btn_fabu);
//        badgeView.setBadgeCount(1);
//        badgeView.setBadgeMargin(0, 5, 0, 0);
//        badgeView.set
//        badgeView.setVisibility(View.GONE);
//        badgeView.setFocusableInTouchMode(false);
//    }

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
                if (currentTab == 1) {
//                    homeFragment.fragmentList.get(homeFragment.mViewPager.getCurrentItem()).mlv.setSelection(0);
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
                StatusBarUtil.initStatusBar(this, R.color.almost_white);
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
                currentTab = 1;
                break;
            case R.id.btn_fabu:
                select(1);
                if (MyApplication.count > 0) {
                    sendCollectInfo();
                }
                currentTab = 2;
//                badgeView.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_faxian:
                select(2);
                if (MyApplication.count > 0) {
                    sendCollectInfo();
                }
                currentTab = 3;
                break;
            case R.id.btn_setting:
                select(3);
                if (MyApplication.count > 0) {
                    sendCollectInfo();
                }
                currentTab = 4;
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
                        .url(Urls.Collection_Url)
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
        btn_fabu = (RadioButton) findViewById(R.id.btn_fabu);
        btn_faxian = (RadioButton) findViewById(R.id.btn_faxian);
        btn_setting = (RadioButton) findViewById(R.id.btn_setting);
        btn_home.setOnClickListener(this);
        btn_fabu.setOnClickListener(this);
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

    @Override
    protected void onResume() {
        super.onResume();
    }
}

