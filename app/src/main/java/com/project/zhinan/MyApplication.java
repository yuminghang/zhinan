package com.project.zhinan;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.project.zhinan.utils.LocationService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ymh on 2016/4/13.
 */
public class MyApplication extends Application {
    public static int count = 0;
    public static double latitude = 0;
    public static double longitude = 0;
    private static List<Activity> activities = new ArrayList<Activity>();
    private static MyApplication myApplication = new MyApplication();
    private static boolean isLogin = false;
    private static List<Activity> tvActivities = new ArrayList<Activity>();
    private static List<Activity> videoActivities = new ArrayList<Activity>();
    private static List<Activity> newsActivities = new ArrayList<Activity>();
    private SharedPreferences sharedPreferences, sharedPreferences1;
    private LocationClient mLocClient;
    public LocationService locationService;

    public MyApplication() {

    }

    public static MyApplication getInstance() {
        return myApplication;
    }

    public static void addActivity(Activity activity) {
        activities.add(activity);
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

    @Override
    public void onCreate() {
        locationService = new LocationService(getApplicationContext());
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        goGetLocation();
        sharedPreferences = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        isLogin = sharedPreferences.getBoolean("isLogin", false);
        sharedPreferences1 = getSharedPreferences("collect_upload_state", Context.MODE_PRIVATE);
        count = sharedPreferences1.getInt("isUpload", 0);
        //初始化科大讯飞语音
        // 将“12345678”替换成您申请的APPID，申请地址：http://open.voicecloud.cn
        SpeechUtility.createUtility(getApplicationContext(), SpeechConstant.APPID + "=58783312");
        super.onCreate();
    }

    private void goGetLocation() {

        LocationClientOption locOption = new LocationClientOption();
        locOption.setIgnoreKillProcess(false);
        locOption.setEnableSimulateGps(true);
        locOption.setCoorType("bd09ll");

        mLocClient = new LocationClient(getApplicationContext());
        mLocClient.setLocOption(locOption);
        mLocClient.registerLocationListener(new BDLocationListener() {

            @Override
            public void onReceiveLocation(BDLocation location) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                Log.e("myapplication", latitude + "");
                Log.e("myapplication", longitude + "");
//                mLatLng = new LatLng(location.getLatitude(),
//                        location.getLongitude());
                // 定位成功后销毁
                mLocClient.stop();
            }
        });
        mLocClient.start();
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
