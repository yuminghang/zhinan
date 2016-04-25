package com.project.zhinan.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.project.zhinan.MyApplication;
import com.project.zhinan.R;
import com.project.zhinan.adapter.channelAdapter;
import com.project.zhinan.bean.shanxiweishibean;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends Activity {

    private ListView listView;
    private List<shanxiweishibean> datasWeishi = new ArrayList<shanxiweishibean>();
    private List<String> list1;
    private channelAdapter channeladapter;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        MyApplication.addNewsActivity(this);
        listView = (ListView) findViewById(R.id.listview);
        initData();
        initListView();
        addListener();
    }

    private void initData() {
        list1 = new ArrayList<String>();
        list1.add(0, "华商报");
        list1.add(1, "陕西农林");
        list1.add(2, "三秦都市报");
        list1.add(3, "西安晚报");
    }

    private void initListView() {
        channeladapter = new channelAdapter(this, list1);
        listView.setAdapter(channeladapter);
    }

    private void addListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (position % 2 == 0) {
                intent = new Intent(NewsActivity.this, NewsDetailActivity.class);
//                intent.putExtra("kind", 0);
                startActivity(intent);
//                } else {
//                    intent = new Intent(NewsActivity.this, TvDetailActivity.class);
//                    intent.putExtra("kind", 1);
//                    startActivity(intent);
//                }
            }
        });
    }

}
