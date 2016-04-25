package com.project.zhinan.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.project.zhinan.MyApplication;
import com.project.zhinan.R;
import com.project.zhinan.adapter.VideoAdapter;
import com.project.zhinan.adapter.channelAdapter;
import com.project.zhinan.adapter.columnAdapter;

import java.util.ArrayList;
import java.util.List;

public class VideoDetailActivity extends AppCompatActivity {
    private columnAdapter columnadapter;
    private ListView listView;
    private Intent intent;
    private List<String> list1;
    private VideoAdapter videoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        MyApplication.addVideoActivity(this);
        listView = (ListView) findViewById(R.id.listview);
        initData();
        initListView();
        addListener();
    }

    private void initData() {
        list1 = new ArrayList<String>();
        list1.add(0, "陕广大视野");
        list1.add(1, "午间播报一小时");
        list1.add(2, "陕广汇天下");
        list1.add(3, "爱心时间");
        list1.add(4, "七十二行");
        list1.add(5, "财经正前方");
        list1.add(6, "晚间新闻浏览");
        list1.add(7, "全省新闻联播");
        list1.add(8, "秦风热线");
        list1.add(9, "秦岭夜话");
        list1.add(10, "文化三秦");
    }

    private void initListView() {
        videoAdapter = new VideoAdapter(this, list1);
        listView.setAdapter(videoAdapter);
    }

    private void addListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(VideoDetailActivity.this, MyCommentActivity.class));
            }
        });
    }
}



