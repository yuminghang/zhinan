package com.project.zhinan;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ymh on 2016/4/13.
 */
public class MyApplication extends Application {
    private static List<Activity> activities = new ArrayList<Activity>();
    private static MyApplication myApplication = new MyApplication();
    private static boolean isLogin = false;
    private SharedPreferences sharedPreferences;

    public MyApplication() {

    }

    @Override
    public void onCreate() {
        sharedPreferences = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        isLogin = sharedPreferences.getBoolean("isLogin", false);
        super.onCreate();
    }

    public static MyApplication getInstance() {
        return myApplication;
    }

    public void exit() {
        try {
            for (Activity activity : activities) {
                if (!activity.isFinishing() && activity != null) {
                    activity.finish();
                }
            }
            // 杀死该应用进程
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {

        }

    }

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public void setLoginIn() {
        isLogin = true;
    }

    public void setLoginOut() {
        isLogin = false;
    }

    public boolean isLogin() {
        return isLogin;
    }
}
