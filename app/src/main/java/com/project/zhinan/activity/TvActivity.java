package com.project.zhinan.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.zhinan.MyApplication;
import com.project.zhinan.R;
import com.project.zhinan.adapter.channelAdapter;
import com.project.zhinan.bean.shanxiweishibean;

import java.util.ArrayList;
import java.util.List;

public class TvActivity extends Activity {
    private ListView listView;
    private List<shanxiweishibean> datasWeishi = new ArrayList<shanxiweishibean>();
    private List<String> list1;
    private channelAdapter channeladapter;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv);
        MyApplication.addTvActivity(this);
        listView = (ListView) findViewById(R.id.listview);
        initData();
        initListView();
        addListener();
    }

    private void initData() {

        list1 = new ArrayList<String>();
        list1.add(0, "陕西卫视");
        list1.add(1, "陕西农林");
        list1.add(2, "陕西教育");
        list1.add(3, "陕西体育");
        list1.add(4, "陕西财经");
        list1.add(5, "陕西新闻");
        list1.add(6, "陕西少儿");
        list1.add(7, "陕西娱乐");
        list1.add(8, "陕西电影");
        list1.add(9, "陕西军事");
        list1.add(10, "陕西剧场");
        list1.add(11, "陕西国际");
        list1.add(12, "陕西科学");
        list1.add(13, "陕西点播");
        list1.add(14, "陕西购物");
        list1.add(15, "陕西百姓");
    }

    private void initListView() {
        channeladapter = new channelAdapter(this, list1);
        listView.setAdapter(channeladapter);
    }

    private void addListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position % 2 == 0) {
                    intent = new Intent(TvActivity.this, TvDetailActivity.class);
                    intent.putExtra("kind", 0);
                    startActivity(intent);
                } else {
                    intent = new Intent(TvActivity.this, TvDetailActivity.class);
                    intent.putExtra("kind", 1);
                    startActivity(intent);
                }
            }
        });
    }

}
