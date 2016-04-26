package com.project.zhinan.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.zhinan.MyApplication;
import com.project.zhinan.R;
import com.project.zhinan.adapter.channelAdapter;
import com.project.zhinan.adapter.columnAdapter;
import com.project.zhinan.bean.shanxiweishibean;

import java.util.ArrayList;
import java.util.List;

public class TvActivity extends Activity {
    private ListView listview_1, listview_2;
    private List<String> list1;
    private List<shanxiweishibean> list2;
    private channelAdapter channeladapter;
    private columnAdapter columnadapter;
    private String jsonDataWeishi = "[{start:'06:01',name: '陕西新闻联播（重）'},{start:'06:24',name: '新视界（重）'},{start:'06:50',name: '早间天气预报'},{start:'06:53',name: '创客时代'},{start:'07:03',name: '纪录时间'},{start:'07:48',name: 'TV1周刊'},{start:'08:07',name: '电视剧：兵出潼关'},{start:'11:15',name: '国风秦韵'},{start:'12:03',name: '中国真功夫'},{start:'13:08',name: '午间天气预报'},{start:'13:11',name: '星app风云榜'},{start:'13:37',name: '电视剧：铁血尖刀'},{start:'18:00',name: '新视界'},{start:'18:27',name: '陕西新闻联播'},{start:'18:53',name: '旅游天气预报'},{start:'19:00',name: '转 中央新闻联播'},{start:'19:35',name: '电视剧：香火'},{start:'21:20',name: '中国真功夫'},{start:'22:30',name: '电视剧：飞哥大英雄'}]";
    private String jsonDataNonglin = " [{start:'06:02',name: '天天农高会'},{start:'06:21',name: '致富故事会'},{start:'06:39',name: '三农大视野'},{start:'08:10',name: '中国农资秀'},{start:'08:27',name: '三农信息联播'},{start:'09:38',name: '电视剧：皮五传奇'},{start:'12:02',name: '天天农高会'},{start:'12:24',name: '致富故事会'},{start:'12:45',name: '中国农资秀'},{start:'14:15',name: '三农大视野'},{start:'14:35',name: '电视剧：专列一号'},{start:'17:00',name: '忙罢戏楼'},{start:'18:13',name: '村里村外'},{start:'18:34',name: '农村大市场'},{start:'18:49',name: '生财有道'},{start:'18:59',name: '天天农高会'},{start:'19:17',name: '创富新生代'},{start:'19:27',name: '三农信息联播'},{start:'20:33',name: '致富招招鲜'},{start:'20:43',name: '中国农资秀'},{start:'21:03',name: '致富故事会'},{start:'21:24',name: '对话一把手'},{start:'21:42',name: '电视剧：大南迁'}]";
    private List<shanxiweishibean> datasWeishi = new ArrayList<shanxiweishibean>();
    private List<shanxiweishibean> datasNonglin = new ArrayList<shanxiweishibean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv);
        MyApplication.addTvActivity(this);
        listview_1 = (ListView) findViewById(R.id.listview_1);
        listview_2 = (ListView) findViewById(R.id.listview_2);
        initData();
        initListView();
        addListener();
    }

    private void initData() {
        Gson gson = new Gson();
        datasWeishi = gson.fromJson(jsonDataWeishi, new TypeToken<List<shanxiweishibean>>() {
        }.getType());
        datasNonglin = gson.fromJson(jsonDataNonglin, new TypeToken<List<shanxiweishibean>>() {
        }.getType());
        list1 = new ArrayList<String>();
        list2 = new ArrayList<shanxiweishibean>();
        list1.add(0, "陕西卫视");
        list1.add(1, "陕西一套");
        list1.add(2, "陕西二套");
        list1.add(3, "陕西三套");
        list1.add(4, "陕西四套");
        list1.add(5, "陕西五套");
        list1.add(6, "陕西六套");
        list1.add(7, "陕西七套");
        list1.add(8, "农林科技");
        list1.add(9, "移动电视");
        list1.add(10, "CCTV-1");
        list1.add(11, "CCTV-2");
        list1.add(12, "CCTV-3");
        list1.add(13, "CCTV-4");
        list1.add(14, "CCTV-5");
        list2.addAll(datasWeishi);
    }

    private void initListView() {
        channeladapter = new channelAdapter(this, list1);
        columnadapter = new columnAdapter(this, list2);
        listview_1.setAdapter(channeladapter);
        listview_2.setAdapter(columnadapter);
    }

    private void addListener() {
        listview_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position >= 10) {
                    Toast.makeText(TvActivity.this, "该栏目相关功能尚未开放", Toast.LENGTH_SHORT).show();
                    return;
                }
                channeladapter.pos = position;
                channeladapter.notifyDataSetChanged();
                list2.clear();
                if (position % 2 == 0) {
                    list2.addAll(datasWeishi);
                } else {
                    list2.addAll(datasNonglin);
                }
                columnadapter.notifyDataSetChanged();
            }
        });
        listview_2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(TvActivity.this, MyCommentActivity.class));
            }
        });
    }

}
