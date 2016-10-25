package com.project.zhinan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.project.zhinan.R;
import com.project.zhinan.adapter.QuestionItemAdapter;
import com.project.zhinan.bean.Detail;
import com.project.zhinan.utils.ConstantValue;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String body = data.getString("body");
            Gson gson = new Gson();
            Detail detail = gson.fromJson(body, Detail.class);
            if (toolbar != null) {
                toolbar.setTitle(detail.getTitle());
            } else {
                toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);
                toolbar.setTitle(detail.getTitle());
            }
            mList.clear();
            mList.addAll(detail.getQuestions());
            adapter.notifyDataSetChanged();
        }
    };
    private ListView listView;
    public static List<Detail.QuestionsBean> mList;
    private QuestionItemAdapter adapter;
    private Button submitBtn;
    private String id;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.lv_detail);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LayoutInflater inflater = getLayoutInflater();
        View inflate = inflater.inflate(R.layout.lv_footer, null);
        submitBtn = (Button) inflate.findViewById(R.id.submit_btn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFinshed()) {
                    Toast.makeText(DetailActivity.this, "问卷提交成功，感谢您的参与", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(DetailActivity.this, "还有未填写问题", Toast.LENGTH_LONG).show();
                }
            }
        });
        listView.addFooterView(inflate);
        adapter = new QuestionItemAdapter(this);
        listView.setAdapter(adapter);
    }

    private boolean isFinshed() {
        JsonObject jsonObject = new JsonObject();
        for (int i = 0; i < mList.size(); i++) {
            Detail.QuestionsBean qb = mList.get(i);
            System.out.println(qb.getAnswer());
            if (qb.getAnswer().equals("")||(qb.getType()==2)&&qb.getAnswer().length()<=4) {
                listView.smoothScrollToPosition(i);
                return false;
            } else {
                jsonObject.addProperty(qb.get_id(), qb.getAnswer());
                if (qb.getType() == 2) {

                    String value = String.valueOf(qb.getAnswer());
                    jsonObject.addProperty(qb.get_id(), value.substring(0, value.length() - 1));
                }
            }
        }
//        for (Detail.QuestionsBean qb :
//                mList) {
//
//            System.out.println(qb.getAnswer());
//            if (qb.getAnswer().equals("")) {
//                return false;
//            } else {
//                jsonObject.addProperty(qb.get_id(), qb.getAnswer());
//                if (qb.getType() == 2) {
//
//                    String value = String.valueOf(qb.getAnswer());
//                    jsonObject.addProperty(qb.get_id(), value.substring(0,value.length()-1));
//                }
//            }
//        }
        senData(jsonObject.toString());

        return true;
    }

    private void senData(String s) {
        final OkHttpClient client = new OkHttpClient();
        System.out.println("answer=" + s);
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "answer=" + s);
        final Request request = new Request.Builder()
                .url(ConstantValue.QUEURL + "/questionnaire/m/" + id)
                .post(body)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .addHeader("cache-control", "no-cache")
                .build();

        new Thread() {
            @Override
            public void run() {
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    System.out.println(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();

    }

    @Override
    protected void onResume() {
        super.onResume();
        new Thread() {
            @Override
            public void run() {
                getDetail();
            }
        }.start();

    }

    public void getDetail() {
        OkHttpClient client = new OkHttpClient();
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        Request request = new Request.Builder()
                .url(ConstantValue.QUEURL + "/user/questionnaire/" + id)
                .get()
                .build();

        try {
            Response response = client.newCall(request).execute();
            Message msg = new Message();
            Bundle bundle = new Bundle();
            bundle.putString("body", response.body().string());
            msg.setData(bundle);
            handler.sendMessage(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
