package com.project.zhinan.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.project.zhinan.MyApplication;
import com.project.zhinan.R;
import com.project.zhinan.adapter.channelAdapter;
import com.project.zhinan.adapter.columnAdapter;
import com.project.zhinan.bean.shanxiweishibean;

import java.util.ArrayList;
import java.util.List;

public class VideoActivity extends Activity {

    private ListView listview_1, listview_2;
    private List<shanxiweishibean> datasWeishi = new ArrayList<shanxiweishibean>();
    private List<String> list1;
    private channelAdapter channeladapter;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        MyApplication.addVideoActivity(this);
        listview_1 = (ListView) findViewById(R.id.listview_1);
        listview_2 = (ListView) findViewById(R.id.listview_2);
        initData();
        initListView();
        addListener();
    }

    private void initData() {
        list1 = new ArrayList<String>();
        list1.add(0, "陕西交通电台");
        list1.add(1, "陕西经济电台");
        list1.add(2, "陕西新闻电台");
        list1.add(3, "陕西音乐电台");
        list1.add(4, "陕西故事电台");
        list1.add(5, "陕西汽车电台");
        list1.add(6, "陕西青春电台");
    }

    private void initListView() {
        channeladapter = new channelAdapter(this, list1);
//        columnadapter = new columnAdapter(this, list2);
        listview_1.setAdapter(channeladapter);
//        listview_2.setAdapter(columnadapter);
    }

    private void addListener() {
        listview_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                channeladapter.pos = position;
                channeladapter.notifyDataSetChanged();
//                list2.clear();
//                if (position % 2 == 0) {
//                    list2.addAll(datasWeishi);
//                } else {
//                    list2.addAll(datasNonglin);
//                }
//                columnadapter.notifyDataSetChanged();
            }
        });
        listview_2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(VideoActivity.this, MyCommentActivity.class));
            }
        });
    }
}




