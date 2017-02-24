package com.project.zhinan.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.project.zhinan.R;
import com.project.zhinan.adapter.CollectionitemAdapter;
import com.project.zhinan.api.Urls;
import com.project.zhinan.bean.CollectionBeans;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyCollection extends AppCompatActivity {

    private static final int NETWORK_EORR = 1;
    private TextView mTitlebarTextView;
    private ListView mMycollectionListView;
    private ArrayList<CollectionBeans.DataBean> myObjects;
    private CollectionitemAdapter adapter;
    private GetTask getTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);
        myObjects = new ArrayList<CollectionBeans.DataBean>();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
        initView();
    }

    private void initData() {
        getTask = new GetTask();
        getTask.execute();

    }

    private class GetTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            OkHttpClient client = new OkHttpClient();
            SharedPreferences cookie = getSharedPreferences("cookie", Context.MODE_PRIVATE);
            String my_cookie = cookie.getString("my_cookie", "");
            Request request = new Request.Builder()
                    .url("http://120.27.41.245:2888/my_collection")
                    .get()
                    .addHeader("cache-control", "no-cache")
                    .addHeader("Cookie", my_cookie)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if (s == null) {
                Toast.makeText(MyCollection.this, "网络错误", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    if (s.contains("success")) {
                        CollectionBeans collectionBeans = (new Gson()).fromJson(s, CollectionBeans.class);
                        List<CollectionBeans.DataBean> data = collectionBeans.getData();
                        myObjects.clear();
                        myObjects.addAll(data);
                        adapter.notifyDataSetChanged();
                    } else {
                        JSONObject jsonObject = new JSONObject(s);
                        Toast.makeText(MyCollection.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MyCollection.this, "json错误", Toast.LENGTH_SHORT).show();
                }

            }
            super.onPostExecute(s);
        }
    }

    private void initView() {
        mMycollectionListView = (ListView) findViewById(R.id.lv_mycollection);
//        adapter = new CollectionAdapter(this, myObjects);
        adapter = new CollectionitemAdapter(this, myObjects);
        mMycollectionListView.setAdapter(adapter);
        mMycollectionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}
