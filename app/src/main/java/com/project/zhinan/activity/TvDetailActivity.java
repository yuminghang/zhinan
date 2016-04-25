package com.project.zhinan.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.zhinan.MyApplication;
import com.project.zhinan.R;
import com.project.zhinan.adapter.columnAdapter;
import com.project.zhinan.bean.shanxiweishibean;

import java.util.ArrayList;
import java.util.List;

public class TvDetailActivity extends Activity {
    private String jsonDataWeishi = "[{start:'06:01',name: '陕西新闻联播（重）'},{start:'06:24',name: '新视界（重）'},{start:'06:50',name: '早间天气预报'},{start:'06:53',name: '创客时代'},{start:'07:03',name: '纪录时间'},{start:'07:48',name: 'TV1周刊'},{start:'08:07',name: '电视剧：兵出潼关'},{start:'11:15',name: '国风秦韵'},{start:'12:03',name: '中国真功夫'},{start:'13:08',name: '午间天气预报'},{start:'13:11',name: '星app风云榜'},{start:'13:37',name: '电视剧：铁血尖刀'},{start:'18:00',name: '新视界'},{start:'18:27',name: '陕西新闻联播'},{start:'18:53',name: '旅游天气预报'},{start:'19:00',name: '转 中央新闻联播'},{start:'19:35',name: '电视剧：香火'},{start:'21:20',name: '中国真功夫'},{start:'22:30',name: '电视剧：飞哥大英雄'}]";
    private String jsonDataNonglin = " [{start:'06:02',name: '天天农高会'},{start:'06:21',name: '致富故事会'},{start:'06:39',name: '三农大视野'},{start:'08:10',name: '中国农资秀'},{start:'08:27',name: '三农信息联播'},{start:'09:38',name: '电视剧：皮五传奇'},{start:'12:02',name: '天天农高会'},{start:'12:24',name: '致富故事会'},{start:'12:45',name: '中国农资秀'},{start:'14:15',name: '三农大视野'},{start:'14:35',name: '电视剧：专列一号'},{start:'17:00',name: '忙罢戏楼'},{start:'18:13',name: '村里村外'},{start:'18:34',name: '农村大市场'},{start:'18:49',name: '生财有道'},{start:'18:59',name: '天天农高会'},{start:'19:17',name: '创富新生代'},{start:'19:27',name: '三农信息联播'},{start:'20:33',name: '致富招招鲜'},{start:'20:43',name: '中国农资秀'},{start:'21:03',name: '致富故事会'},{start:'21:24',name: '对话一把手'},{start:'21:42',name: '电视剧：大南迁'}]";
    private columnAdapter columnadapter;
    private ListView listView;
    private List<shanxiweishibean> list2 = new ArrayList<shanxiweishibean>();
    private List<shanxiweishibean> datasWeishi = new ArrayList<shanxiweishibean>();
    private List<shanxiweishibean> datasNonglin = new ArrayList<shanxiweishibean>();
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_detail);
        MyApplication.addTvActivity(this);
        initData();
        i = getIntent().getIntExtra("kind", 0);
        if (i == 0) {
            list2.clear();
            list2.addAll(datasWeishi);
        } else {
            list2.clear();
            list2.addAll(datasNonglin);
        }
        Log.e("12321321321321", i + "");
        initListView();
        addListener();
    }

    private void addListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(TvDetailActivity.this, MyCommentActivity.class));
            }
        });
    }

    private void initListView() {
        listView = (ListView) findViewById(R.id.listview);
        columnadapter = new columnAdapter(this, list2);
        listView.setAdapter(columnadapter);
    }

    private void initData() {
        Gson gson = new Gson();
        datasWeishi = gson.fromJson(jsonDataWeishi, new TypeToken<List<shanxiweishibean>>() {
        }.getType());
        datasNonglin = gson.fromJson(jsonDataNonglin, new TypeToken<List<shanxiweishibean>>() {
        }.getType());
    }
}
