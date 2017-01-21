package com.project.zhinan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.google.gson.Gson;
import com.project.zhinan.MyApplication;
import com.project.zhinan.R;
import com.project.zhinan.api.Urls;
import com.project.zhinan.bean.posBean;
import com.project.zhinan.utils.LocationService;
import com.project.zhinan.utils.Utils;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MapActivity extends Activity {

    public static Map<String, String> pointList = new HashMap<>();
    private MapView mMapView = null;
    private BaiduMap mBaiduMap;
    private Button reset;
    private LocationService locService;
    private LinkedList<LocationEntity> locationList = new LinkedList<LocationEntity>(); // 存放历史定位结果的链表，最大存放当前结果的前5次定位结果
    private Response response;
    private String resPos;
    private posBean posList;
    /***
     * 接收定位结果消息，并显示在地图上
     */
    private Handler locHander = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    try {
                        BDLocation location = msg.getData().getParcelable("loc");
                        int iscal = msg.getData().getInt("iscalculate");
                        if (location != null) {
                            LatLng point = new LatLng(location.getLatitude(), location.getLongitude());
                            // 构建Marker图标
                            BitmapDescriptor bitmap = null;
                            if (iscal == 0) {
                                bitmap = BitmapDescriptorFactory.fromResource(R.drawable.qq); // 非推算结果
                            } else {
                                bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_openmap_focuse_mark); // 推算结果
                            }

                            // 构建MarkerOption，用于在地图上添加Marker
                            OverlayOptions option = new MarkerOptions().position(point).icon(bitmap);

                            // 在地图上添加Marker，并显示
                            mBaiduMap.addOverlay(option);
                            mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(point));
                        }
                    } catch (Exception e) {
                    }
                    break;
                case 1:
                    addPoints(posList.getData());
                    break;
            }

        }

    };
    /***
     * 定位结果回调，在此方法中处理定位结果
     */
    BDLocationListener listener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {

            if (location != null && (location.getLocType() == 161 || location.getLocType() == 66)) {
                Message locMsg = locHander.obtainMessage();
                Bundle locData;
                locData = Algorithm(location);
                if (locData != null) {
                    locData.putParcelable("loc", location);
                    locMsg.setData(locData);
                    locMsg.what = 0;
                    locHander.sendMessage(locMsg);
                    Log.e("sss", "asdsad");
                }
            }
        }
    };

    private void addPoints(List<posBean.DataEntity> list) {
        for (int i = 0; i < list.size(); i++) {
            String key = "" + list.get(i).getLat() + list.get(i).getLon();
            if (!pointList.containsKey(key)) {
                pointList.put(key, list.get(i).getAd_order());
            }
            LatLng point = new LatLng(list.get(i).getLat(), list.get(i).getLon());
            // 构建Marker图标
            BitmapDescriptor bitmap = null;
            bitmap = BitmapDescriptorFactory.fromResource(R.drawable.qqbig); // 非推算结果
            // 构建MarkerOption，用于在地图上添加Marker
            OverlayOptions option = new MarkerOptions().position(point).icon(bitmap);
            // 在地图上添加Marker，并显示
            mBaiduMap.addOverlay(option);
            mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(point));
            Toast.makeText(MapActivity.this, list.get(i).getUser().getName(), Toast.LENGTH_SHORT).show();
        }
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Log.e("wwww", marker.getPosition().latitude + "  " + marker.getPosition().longitude);
                String pointKey = "" + marker.getPosition().latitude + marker.getPosition().longitude;
                String ad_order = pointList.get(pointKey);
                Intent intent = new Intent(MapActivity.this, QianggouDetailActivity2.class);
                intent.putExtra("ad_order", ad_order);
                startActivity(intent);
                return true;
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mMapView = (MapView) findViewById(R.id.bmapView);
//        reset = (Button) findViewById(R.id.clear);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(15));
        locService = ((MyApplication) getApplication()).locationService;
        LocationClientOption mOption = locService.getDefaultLocationClientOption();
        mOption.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
        mOption.setCoorType("bd09ll");
        //locService.setLocationOption(mOption);
        //locService.registerListener(listener);
        //locService.start();

        LocationClient mClient = new LocationClient(this);
        mClient.setLocOption(mOption);
        mClient.registerLocationListener(listener);
        mClient.start();

        getNearByPosData();//获取附近的位置信息
    }


    /***
     * 平滑策略代码实现方法，主要通过对新定位和历史定位结果进行速度评分，
     * 来判断新定位结果的抖动幅度，如果超过经验值，则判定为过大抖动，进行平滑处理,若速度过快，
     * 则推测有可能是由于运动速度本身造成的，则不进行低速平滑处理 ╭(●｀∀´●)╯
     *
     * @param
     * @return Bundle
     */
    private Bundle Algorithm(BDLocation location) {
        Bundle locData = new Bundle();
        double curSpeed = 0;
        if (locationList.isEmpty() || locationList.size() < 2) {
            LocationEntity temp = new LocationEntity();
            temp.location = location;
            temp.time = System.currentTimeMillis();
            locData.putInt("iscalculate", 0);
            locationList.add(temp);
        } else {
            if (locationList.size() > 5)
                locationList.removeFirst();
            double score = 0;
            for (int i = 0; i < locationList.size(); ++i) {
                LatLng lastPoint = new LatLng(locationList.get(i).location.getLatitude(),
                        locationList.get(i).location.getLongitude());
                LatLng curPoint = new LatLng(location.getLatitude(), location.getLongitude());
                double distance = DistanceUtil.getDistance(lastPoint, curPoint);
                curSpeed = distance / (System.currentTimeMillis() - locationList.get(i).time) / 1000;
                score += curSpeed * Utils.EARTH_WEIGHT[i];
            }
            if (score > 0.00000999 && score < 0.00005) { // 经验值,开发者可根据业务自行调整，也可以不使用这种算法
                location.setLongitude(
                        (locationList.get(locationList.size() - 1).location.getLongitude() + location.getLongitude())
                                / 2);
                location.setLatitude(
                        (locationList.get(locationList.size() - 1).location.getLatitude() + location.getLatitude())
                                / 2);
                locData.putInt("iscalculate", 1);
            } else {
                locData.putInt("iscalculate", 0);
            }
            LocationEntity newLocation = new LocationEntity();
            newLocation.location = location;
            newLocation.time = System.currentTimeMillis();
            locationList.add(newLocation);

        }
        return locData;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
//		WriteLog.getInstance().close();
        locService.unregisterListener(listener);
        locService.stop();
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
//        reset.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                if (mBaiduMap != null)
//                    mBaiduMap.clear();
//            }
//        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();

    }

    public void getNearByPosData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(Urls.Map_Url)
                        .get()
                        .addHeader("cache-control", "no-cache")
                        .addHeader("postman-token", "c3e76af9-a4ca-d71d-e5bf-fdb041a46bd1")
                        .build();
                try {
                    response = client.newCall(request).execute();
                    resPos = response.body().string();
                    Gson gson = new Gson();
                    posList = gson.fromJson(resPos, posBean.class);
                    locHander.sendEmptyMessage(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 封装定位结果和时间的实体类
     *
     * @author baidu
     */
    class LocationEntity {
        BDLocation location;
        long time;
    }
}
