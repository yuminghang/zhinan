package com.project.zhinan.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.project.zhinan.R;
import com.project.zhinan.dao.HistorySqlliteHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyCollection extends AppCompatActivity {

    private TextView mTitlebarTextView;
    private ListView mMycollectionListView;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> listems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);
        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        HistorySqlliteHelper historySqlliteHelper = new HistorySqlliteHelper(MyCollection.this);
        SQLiteDatabase readableDatabase = historySqlliteHelper.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery("select * from history_table where 1", null);
        while (cursor.moveToNext()){
            String ad_url = cursor.getString(cursor.getColumnIndex("ad_url"));
            Map<String, Object> listem = new HashMap<String, Object>();
            listem.put("name", ad_url);
            listems.add(listem);
        }
        cursor.close();
        readableDatabase.close();
    }

    private void initView() {
        mMycollectionListView = (ListView) findViewById(R.id.lv_mycollection);
        listems = new ArrayList<Map<String, Object>>();
        adapter = new SimpleAdapter(MyCollection.this, listems, R.layout.collectionitem, new String[]{"name"}, new int[]{R.id.item_name});
        mMycollectionListView.setAdapter(adapter);
    }
}
