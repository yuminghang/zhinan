package com.project.zhinan.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.radar.RadarNearbyInfo;
import com.baidu.mapapi.radar.RadarNearbyResult;
import com.baidu.mapapi.radar.RadarNearbySearchOption;
import com.baidu.mapapi.radar.RadarSearchError;
import com.baidu.mapapi.radar.RadarSearchListener;
import com.baidu.mapapi.radar.RadarSearchManager;
import com.baidu.mapapi.radar.RadarUploadInfo;
import com.project.zhinan.R;

public class RadarActivity extends Activity {

    private final String TAG = "xxxx";
    private LocationClient mLocClient;

    private RadarSearchManager mManager;
    private LatLng mLatLng;
    private RadarSearchListener mRSListener = new RadarSearchListener() {
        @Override
        public void onGetUploadState(RadarSearchError err) {
            Log.i(TAG, "UploadState");
            if (err == RadarSearchError.RADAR_NO_ERROR) {
                Log.i(TAG, "UploadState OK");
                mHandler.sendEmptyMessage(3);
            } else {
                Log.i(TAG, "UploadState err:" + err);
                mHandler.sendEmptyMessage(4);
            }
        }

        @Override
        public void onGetNearbyInfoList(RadarNearbyResult result, RadarSearchError err) {
            Log.i(TAG, "NearbyInfoList");
            if (err == RadarSearchError.RADAR_NO_ERROR) {
                Log.i(TAG, "NearbyInfoList OK");
//              totalNum:3
//              pageIndex:0
//              pageNum:1
//              infoList.size:3
//              info userid:123
//              info distance:814
//              info comments:1
//              info timeStamp:Mon Feb 15 18:24:49 格林尼治标准时间+0800 2016
                Log.i(TAG, "totalNum:" + result.totalNum);  // 总结果个数
                Log.i(TAG, "pageIndex:" + result.pageIndex);    // 页码
                Log.i(TAG, "pageNum:" + result.pageNum);    // 总页数
                Log.i(TAG, "infoList.size:" + result.infoList.size());
                for (int i = 0; i < result.infoList.size(); i++) {
                    RadarNearbyInfo info = result.infoList.get(i);
                    Log.i(TAG, "info userid:" + info.userID);   // userid
                    Log.i(TAG, "info distance:" + info.distance);   // 距离
                    Log.i(TAG, "info comments:" + info.comments);   // 备注
                    Log.i(TAG, "info timeStamp:" + info.timeStamp); // 上传位置时的时间戳
                    Log.i(TAG, "info latitude:" + info.pt.latitude); // 经度
                    Log.i(TAG, "info longitude:" + info.pt.longitude); // 纬度
                }
            } else {
                Log.i(TAG, "NearbyInfoList err:" + err);
            }

            mHandler.sendEmptyMessage(4);
        }

        @Override
        public void onGetClearInfoState(RadarSearchError err) {
            Log.i(TAG, "ClearInfoState");
            if (err == RadarSearchError.RADAR_NO_ERROR) {
                Log.i(TAG, "ClearInfoState OK");
            } else {
                Log.i(TAG, "ClearInfoState err:" + err);
            }
            mHandler.sendEmptyMessage(5);
        }
    };
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    goStartRadar();
                    break;
                case 1:
                    goGetLocation();
                    break;
                case 2:
                    goUploadInfo(mLatLng);
                    break;
                case 3:
                    goRadar(mLatLng);
                    break;
                case 4:
                    goClear();
                    break;
                case 5:
                    goDestory();
                    break;

                default:
                    break;
            }
        }
    };

    public void startRadar() {
        Log.i(TAG, "startRadar");
        mHandler.sendEmptyMessage(0);
    }

    private void goStartRadar() {
        Log.i(TAG, "goStartRadar");
        mManager = RadarSearchManager.getInstance();
        mManager.setUserID("");
        mManager.addNearbyInfoListener(mRSListener);
        // 定位
        mHandler.sendEmptyMessage(1);
    }

    private void goGetLocation() {
        Log.i(TAG, "goGetLocation");

        LocationClientOption locOption = new LocationClientOption();
        locOption.setIgnoreKillProcess(false);
        locOption.setEnableSimulateGps(true);
        locOption.setCoorType("bd09ll");

        mLocClient = new LocationClient(RadarActivity.this);
        mLocClient.setLocOption(locOption);
        mLocClient.registerLocationListener(new BDLocationListener() {

            @Override
            public void onReceiveLocation(BDLocation location) {
                Log.i(TAG, "onReceiveLocation");

                mLatLng = new LatLng(location.getLatitude(),
                        location.getLongitude());
                mHandler.sendEmptyMessage(2);

                // 定位成功后销毁
                mLocClient.stop();
            }
        });
        mLocClient.start();
    }

    private void goUploadInfo(LatLng ll) {
        Log.i(TAG, "goUploadInfo");
        RadarUploadInfo info = new RadarUploadInfo();
        info.comments = "哈哈哈";
        info.pt = ll;

        mManager.uploadInfoRequest(info);
    }

    private void goRadar(LatLng ll) {
        Log.i(TAG, "goRadar");

        RadarNearbySearchOption option = new RadarNearbySearchOption();
        option.centerPt(ll);    // 中心点
        option.pageCapacity(5);    // 每页包含的结果数
        option.pageNum(0);  // 当前需要查询的页码index，从0开始
        option.radius(1000);    // 搜索半径

        mManager.nearbyInfoRequest(option);
    }

    private void goClear() {
        Log.i(TAG, "goClear");
        mManager.clearUserInfo();
    }

    private void goDestory() {
        Log.i(TAG, "goDestory");
        mManager.removeNearbyInfoListener(mRSListener);
        mManager.destroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar);
        SDKInitializer.initialize(getApplicationContext());
        // 开始
        startRadar();
    }

}
