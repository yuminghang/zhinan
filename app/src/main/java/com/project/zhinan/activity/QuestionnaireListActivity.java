package com.project.zhinan.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.project.zhinan.R;
import com.project.zhinan.adapter.QuestionnaireItemAdapter;
import com.project.zhinan.bean.QuestionnaireItem;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionnaireListActivity extends AppCompatActivity {
    private static final int GETQUESTIONDATA = 1;
    private OkHttpClient client = null;
    private ListView questionnaireList;
    private QuestionnaireItemAdapter adapter;
    private List<QuestionnaireItem.QuestionnairesBean> questionnaires;
    private Toolbar toolbar;
    private TextView tv;
    private int count = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GETQUESTIONDATA:
                    adapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tv = (TextView) findViewById(R.id.tv);
        setSupportActionBar(toolbar);
        questionnaireList = (ListView) findViewById(R.id.lv_content_questionnaire_list);
        questionnaires = new ArrayList<>();
        adapter = new QuestionnaireItemAdapter(this, questionnaires);
        questionnaireList.setAdapter(adapter);
        questionnaireList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (count > 3) {
                    tv.setVisibility(View.GONE);
                } else {
                    count++;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {


            }
        });
        questionnaireList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getActivity(), WenjuanDetailActivity.class);
//                intent.putExtra("question_id", questionnaires.get(position).get_id());
//                startActivity(intent);
                Intent intent = new Intent(QuestionnaireListActivity.this, DetailActivity.class);
                intent.putExtra("id", questionnaires.get(position).get_id());
                startActivity(intent);
            }
        });
        client = new OkHttpClient();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Thread() {
            @Override
            public void run() {
                getList();
            }
        }.start();
        if (toolbar != null) {
            toolbar.setTitle("问卷调查");
        } else {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            toolbar.setTitle("问卷调查");
        }

    }

    public void getList() {

        Request request = new Request.Builder()
                .url("http://115.159.0.155:8080/data/questionnairesList")
                .get()
                .addHeader("cache-control", "no-cache")
                .build();

        try {
            Response response = client.newCall(request).execute();
            Gson gson = new Gson();
            QuestionnaireItem questionnaireItem = gson.fromJson(response.body().string(), QuestionnaireItem.class);
            if (questionnaireItem.isSuccess()) {
                questionnaires.clear();
                questionnaires.addAll(questionnaireItem.getQuestionnaires());
                handler.sendEmptyMessage(GETQUESTIONDATA);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Context getActivity() {
        return this;
    }
}
