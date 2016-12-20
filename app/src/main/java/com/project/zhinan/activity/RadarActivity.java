package com.project.zhinan.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.baidu.mapapi.radar.RadarNearbySearchOption;
import com.baidu.mapapi.radar.RadarSearchError;
import com.baidu.mapapi.radar.RadarSearchManager;
import com.baidu.mapapi.radar.RadarUploadInfo;
import com.project.zhinan.R;

public class RadarActivity extends Activity {

    private RadarSearchManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar);
        mManager = RadarSearchManager.getInstance();
        uploadPos();
        searchNearBy();
    }

    private void searchNearBy() {
        //构造请求参数，其中centerPt是自己的位置坐标
        RadarNearbySearchOption option = new RadarNearbySearchOption().centerPt(pt).pageNum(pageIndex).radius(2000);
//发起查询请求
        mManager.nearbyInfoRequest(option);
        @Override
        public void onGetNearbyInfoList (RadarNearbyResult result, RadarSearchError error){
            // TODO Auto-generated method stub
            if (error == RadarSearchError.RADAR_NO_ERROR) {
                Toast.makeText(RadarActivity.this, "查询周边成功", Toast.LENGTH_LONG)
                        .show();
                //获取成功，处理数据
            } else {
                //获取失败
                Toast.makeText(RadarActivity.this, "查询周边失败", Toast.LENGTH_LONG)
                        .show();
            }
        }
    }

    private void uploadPos() {
        //设置自动上传的callback和时间间隔
        mManager.startUploadAuto(this, 5000);
        //实现上传callback，自动上传
        @Override
        public RadarUploadInfo OnUploadInfoCallback () {
            // TODO Auto-generated method stub
            RadarUploadInfo info = new RadarUploadInfo();
            info.comments = "用户备注信息";
            info.pt = pt;
            return info;
        }
    }
}
