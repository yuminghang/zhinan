package com.project.zhinan.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.project.zhinan.R;
import com.project.zhinan.adapter.CollectionAdapter;
import com.project.zhinan.base.fragment.BaseFragment;
import com.project.zhinan.bean.jsonbean;
import com.project.zhinan.dao.HistorySqlliteHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyCollection extends AppCompatActivity {

    private TextView mTitlebarTextView;
    private ListView mMycollectionListView;
    private CollectionAdapter adapter;
    private ArrayList<jsonbean.ResultEntity.ItemsEntity.BrandsEntity> myObjects = new ArrayList<jsonbean.ResultEntity.ItemsEntity.BrandsEntity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
        initView();
    }

    private void initData() {
        SharedPreferences sharedPreferences = getSharedPreferences("collect", Context.MODE_PRIVATE);
        Map<String, Integer> allContent = (Map<String, Integer>) sharedPreferences.getAll();
        //注意遍历map的方法
        for (Map.Entry<String, Integer> entry : allContent.entrySet()) {
            if (entry.getValue() == 1) {
                myObjects.add(BaseFragment.datas.getResult().getItems().getBrands().get(Integer.parseInt(entry.getKey())));
            }
        }
    }

    private void initView() {
        mMycollectionListView = (ListView) findViewById(R.id.lv_mycollection);
        adapter = new CollectionAdapter(this, myObjects);
        mMycollectionListView.setAdapter(adapter);
    }
}
