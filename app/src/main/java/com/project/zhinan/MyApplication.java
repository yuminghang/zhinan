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
    private SharedPreferences sharedPreferences, sharedPreferences1;
    public static int count = 0;
    private static List<Activity> tvActivities = new ArrayList<Activity>();
    private static List<Activity> videoActivities = new ArrayList<Activity>();
    private static List<Activity> newsActivities = new ArrayList<Activity>();


    public MyApplication() {

    }

    @Override
    public void onCreate() {
        sharedPreferences = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        isLogin = sharedPreferences.getBoolean("isLogin", false);
        sharedPreferences1 = getSharedPreferences("collect_upload_state", Context.MODE_PRIVATE);
        count = sharedPreferences1.getInt("isUpload", 0);
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

    public static void addTvActivity(Activity activity) {
        tvActivities.add(activity);
    }

    public static void closeAllTvActivity() {
        for (Activity activity1 : tvActivities) {
            if (!activity1.isFinishing() && activity1 != null) {
                activity1.finish();
            }
        }
    }

    public static void addVideoActivity(Activity activity) {
        videoActivities.add(activity);
    }

    public static void closeAllVideoActivity() {
        for (Activity activity1 : videoActivities) {
            if (!activity1.isFinishing() && activity1 != null) {
                activity1.finish();
            }
        }
    }

    public static void addNewsActivity(Activity activity) {
        newsActivities.add(activity);
    }

    public static void closeAllNewsActivity() {
        for (Activity activity1 : newsActivities) {
            if (!activity1.isFinishing() && activity1 != null) {
                activity1.finish();
            }
        }
    }

}
