package com.project.zhinan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.project.zhinan.R;
import com.project.zhinan.adapter.MyUploadItemAdapter;
import com.project.zhinan.bean.MyUploadBean;
import com.project.zhinan.net.HttpUtils;

import java.util.ArrayList;

public class MyUpload extends Activity {

    private static final int GETMYPOST = 1;
    private TextView mTitlebarTextView;
    private ListView mMyuploadListView;
    private ArrayList<MyUploadBean.DataBean> objects;
    private MyUploadItemAdapter adapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GETMYPOST:
                    if (myUploadInfo.contains("success")) {
                        Toast.makeText(MyUpload.this, "查询到数据", Toast.LENGTH_SHORT).show();
                        Gson gson = new Gson();
                        MyUploadBean myUploadBean = gson.fromJson(myUploadInfo, MyUploadBean.class);
                        objects.clear();
                        objects.addAll(myUploadBean.getData());
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(MyUpload.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
        }
    };
    private String myUploadInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_upload);
        mMyuploadListView = (ListView) findViewById(R.id.lv_myupload);
        objects = new ArrayList<>();
        adapter = new MyUploadItemAdapter(this, objects);
        mMyuploadListView.setAdapter(adapter);
        mMyuploadListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyUpload.this, QianggouDetailActivity2.class);
                String ad_order = objects.get(position).getOrderno();
                intent.putExtra("ad_order", ad_order);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                myUploadInfo = HttpUtils.doGetWithCookie("http://120.27.41.245:2888/getAdsByUser", MyUpload.this);
                handler.sendEmptyMessage(GETMYPOST);
            }
        };
        thread.start();

    }
}
