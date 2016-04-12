package com.project.zhinan.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.project.zhinan.R;
import com.project.zhinan.fragment.FabuFragment;
import com.project.zhinan.fragment.FaxianFragment;
import com.project.zhinan.fragment.HomeFragment;
import com.project.zhinan.fragment.SettingFragment;
import com.readystatesoftware.systembartint.SystemBarTintManager;


public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private long exitTime;

    FrameLayout fragment_container;
    HomeFragment homeFragment;
    SettingFragment settingFragment;
    FaxianFragment faxianFragment;
    FabuFragment fabuFragment;
    RadioButton btn_home, btn_dongtu, btn_faxian, btn_setting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initStatusBar();

        fragment_container = (FrameLayout) findViewById(R.id.fragment_container);
        initRadioButton();
        select(0);
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
                break;
            case 1:
                if (fabuFragment == null) {
                    fabuFragment = new FabuFragment(this);
                    ft.add(R.id.fragment_container, fabuFragment);
                } else {
                    ft.show(fabuFragment);
                }
                break;
            case 2:
                if (faxianFragment == null) {
                    faxianFragment = new FaxianFragment();
                    ft.add(R.id.fragment_container, faxianFragment);
                } else {
                    ft.show(faxianFragment);
                }
                break;
            case 3:
                if (settingFragment == null) {
                    settingFragment = new SettingFragment();
                    ft.add(R.id.fragment_container, settingFragment);
                } else {
                    ft.show(settingFragment);
                }
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
                break;
            case R.id.btn_faxian:
                select(2);
                break;
            case R.id.btn_setting:
                select(3);
                break;
        }
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
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.title_color);//通知栏所需颜色
    }

    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

}

