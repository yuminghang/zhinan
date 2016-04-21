package com.project.zhinan.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.zhinan.R;
import com.project.zhinan.adapter.CollectionAdapter;
import com.project.zhinan.base.fragment.BaseFragment;
import com.project.zhinan.bean.jsonbean;
import com.project.zhinan.net.HttpUtils;
import com.project.zhinan.utils.ConstantValue;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class MyCollection extends AppCompatActivity {

    private static final int NETWORK_EORR = 1;
    private TextView mTitlebarTextView;
    private ListView mMycollectionListView;
    private CollectionAdapter adapter;
    private ArrayList<jsonbean.ResultEntity.ItemsEntity.BrandsEntity> myObjects = new ArrayList<jsonbean.ResultEntity.ItemsEntity.BrandsEntity>();
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case NETWORK_EORR:

                    Toast.makeText(MyCollection.this, "网络错误", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };
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


        Thread thread = new Thread() {
            @Override
            public void run() {
                /**
                 * 网络查询收藏
                 */
                String getWithCookie = HttpUtils.doGetWithCookie(ConstantValue.CollectionUrl, getApplicationContext());
                if (getWithCookie.contains("success")){
                    //查询成功
                    try {
                        JSONObject jsonObject = new JSONObject(getWithCookie);
                        String coll = jsonObject.getString("coll");
                        System.out.println(coll );

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    //没有数据
                    handler.sendEmptyMessage(NETWORK_EORR);
                }


            }
        };
        thread.start();
    }

    private void initView() {
        mMycollectionListView = (ListView) findViewById(R.id.lv_mycollection);
        adapter = new CollectionAdapter(this, myObjects);
        mMycollectionListView.setAdapter(adapter);
    }
}
