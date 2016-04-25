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
import com.project.zhinan.adapter.NewsAdapter;
import com.project.zhinan.adapter.channelAdapter;
import com.project.zhinan.bean.shanxiweishibean;

import java.util.ArrayList;
import java.util.List;

public class NewsDetailActivity extends Activity {
    private ListView listView;
    private List<shanxiweishibean> datasWeishi = new ArrayList<shanxiweishibean>();
    private List<String> list1;
    private NewsAdapter newsAdapter;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        MyApplication.addNewsActivity(this);
        listView = (ListView) findViewById(R.id.listview);
        initData();
        initListView();
        addListener();
    }

    private void initData() {
        list1 = new ArrayList<String>();
        list1.add(0, "西安新闻");
        list1.add(1, "陕西新闻");
        list1.add(2, "YOU新闻");
        list1.add(3, "国际新闻");
        list1.add(4, "娱乐新闻");
        list1.add(5, "体育新闻");
        list1.add(6, "华商时评");
        list1.add(7, "财经新闻");
    }

    private void initListView() {
        newsAdapter = new NewsAdapter(this, list1);
        listView.setAdapter(newsAdapter);
    }

    private void addListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (position % 2 == 0) {
                intent = new Intent(NewsDetailActivity.this, MyCommentActivity.class);
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
