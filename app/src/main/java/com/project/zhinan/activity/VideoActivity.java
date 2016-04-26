package com.project.zhinan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.zhinan.MyApplication;
import com.project.zhinan.R;
import com.project.zhinan.adapter.channelAdapter;
import com.project.zhinan.adapter.videoDetailAdapter;
import com.project.zhinan.bean.shanxiweishibean;
import com.project.zhinan.bean.videobean;

import java.util.ArrayList;
import java.util.List;

public class VideoActivity extends Activity {

    private ListView listview_1, listview_2;
    private List<shanxiweishibean> datasWeishi = new ArrayList<shanxiweishibean>();
    private List<String> list1;
    private channelAdapter channeladapter;
    private Intent intent;
    private videoDetailAdapter videodetailadapter;
    private List<videobean> datasVideos = new ArrayList<videobean>();
    private List<videobean> datasVideos2 = new ArrayList<videobean>();
    private List<videobean> list2 = new ArrayList<videobean>();
    private String videodatas = "[{\"time\":\"00:00\",\"name\":\"健康故事\"},{\"time\":\"05:00\",\"name\":\"603书场\"},{\"time\":\"06:00\",\"name\":\"健康故事\"},{\"time\":\"07:00\",\"name\":\"纪实风云\"},{\"time\":\"08:00\",\"name\":\"健康故事\"},{\"time\":\"09:00\",\"name\":\"秦人秦事\"},{\"time\":\"10:00\",\"name\":\"健康故事\"},{\"time\":\"11:00\",\"name\":\"民俗老碗荟\"},{\"time\":\"12:00\",\"name\":\"健康故事\"},{\"time\":\"13:00\",\"name\":\"谝闲传\"},{\"time\":\"14:00\",\"name\":\"健康故事\"},{\"time\":\"15:00\",\"name\":\"小说长廊\"},{\"time\":\"16:00\",\"name\":\"健康故事\"},{\"time\":\"17:00\",\"name\":\"故事有约之天下故事会\"},{\"time\":\"18:00\",\"name\":\"健康故事\"},{\"time\":\"19:00\",\"name\":\"热播剧场\"},{\"time\":\"20:00\",\"name\":\"流行阅读\"},{\"time\":\"20:30\",\"name\":\"健康故事\"},{\"time\":\"21:00\",\"name\":\"岔心慌\"},{\"time\":\"22:00\",\"name\":\"健康故事\"},{\"time\":\"23:00\",\"name\":\"武侠客栈\"}]";
    private String videodatas2 = "[{\"time\":\"01:00\",\"name\":\"星光剧场\"},{\"time\":\"02:00\",\"name\":\"陕广新闻\"},{\"time\":\"02:10\",\"name\":\"相伴到黎明\"},{\"time\":\"04:00\",\"name\":\"医疗热线\"},{\"time\":\"04:58\",\"name\":\"开始曲\"},{\"time\":\"05:00\",\"name\":\"健康新呼吸\"},{\"time\":\"06:00\",\"name\":\"陕西新闻\"},{\"time\":\"06:30\",\"name\":\"转播新闻和报纸摘要\"},{\"time\":\"07:00\",\"name\":\"陕西新闻（重播）\"},{\"time\":\"07:30\",\"name\":\"秦风热线\"},{\"time\":\"08:00\",\"name\":\"新闻新观察\"},{\"time\":\"08:30\",\"name\":\"健康视野\"},{\"time\":\"10:20\",\"name\":\"陕广新闻\"},{\"time\":\"10:30\",\"name\":\"新陕西\"},{\"time\":\"11:00\",\"name\":\"健康新节拍\"},{\"time\":\"12:00\",\"name\":\"午间播报\"},{\"time\":\"12:30\",\"name\":\"说法时间\"},{\"time\":\"13:00\",\"name\":\"陕广新闻\"},{\"time\":\"13:05\",\"name\":\"现代新农村\"},{\"time\":\"13:40\",\"name\":\"戏曲大观园\"},{\"time\":\"15:00\",\"name\":\"百姓寻医\"},{\"time\":\"18:00\",\"name\":\"全省新闻联播\"},{\"time\":\"18:30\",\"name\":\"医疗热线\"},{\"time\":\"19:00\",\"name\":\"文化三秦\"},{\"time\":\"20:00\",\"name\":\"杏林百家\"},{\"time\":\"21:20\",\"name\":\"陕广新闻\"},{\"time\":\"21:30\",\"name\":\"阅读与欣赏\"},{\"time\":\"22:00\",\"name\":\"在人间\"},{\"time\":\"23:00\",\"name\":\"心海夜航\"},{\"time\":\"00:00\",\"name\":\"空中门诊\"}]";

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
        Gson gson = new Gson();
        datasVideos = gson.fromJson(videodatas, new TypeToken<List<videobean>>() {
        }.getType());
        datasVideos2 = gson.fromJson(videodatas2, new TypeToken<List<videobean>>() {
        }.getType());
        list1 = new ArrayList<String>();
        list1.add(0, "陕西交通");
        list1.add(1, "陕西经济");
        list1.add(2, "陕西新闻");
        list1.add(3, "陕西音乐");
        list1.add(4, "陕西故事");
        list1.add(5, "陕西汽车");
        list1.add(6, "陕西青春");
        list2.addAll(datasVideos);
    }

    private void initListView() {
        channeladapter = new channelAdapter(this, list1);
        videodetailadapter = new videoDetailAdapter(this, list2);
        listview_1.setAdapter(channeladapter);
        listview_2.setAdapter(videodetailadapter);
    }

    private void addListener() {
        listview_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                channeladapter.pos = position;
                channeladapter.notifyDataSetChanged();
                list2.clear();
                if (position % 2 == 0) {
                    list2.addAll(datasVideos);
                } else {
                    list2.addAll(datasVideos2);
                }
                videodetailadapter.notifyDataSetChanged();
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




