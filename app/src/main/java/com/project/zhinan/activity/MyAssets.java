package com.project.zhinan.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;
import com.project.zhinan.R;
import com.project.zhinan.api.Urls;
import com.project.zhinan.bean.InfoBean;
import com.project.zhinan.net.HttpUtils;

public class MyAssets extends AppCompatActivity {

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mCurrentAccTextView.setText(infoBean.getSub().getAccount() + " 积分\n" +infoBean.getSub().getYue() + " 余额\n"
                    +infoBean.getSub().getQuan().length + " 张优惠券\n");
        }
    };
    private TextView mNoCrashTextView;
    private TextView mCurrentAccTextView;
    private InfoBean infoBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_assets);
        initView();


    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
//        SharedPreferences sharedPreferences = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
//        int account = sharedPreferences.getInt("account", 0);
//        if (account == 0) {
//            mNoCrashTextView.setVisibility(View.VISIBLE);
//        } else {
//            mNoCrashTextView.setVisibility(View.GONE);
//        }
        new Thread() {
            @Override
            public void run() {
                String data = HttpUtils.doGetWithCookie(Urls.get_Account_Info_Url, MyAssets.this);
                Gson gson = new Gson();
                infoBean = gson.fromJson(data, InfoBean.class);
                handler.sendEmptyMessage(0);
            }
        }.start();

    }

    private void initView() {
        mNoCrashTextView = (TextView) findViewById(R.id.noCrash);
        mCurrentAccTextView = (TextView) findViewById(R.id.currentAcc);


    }
}
