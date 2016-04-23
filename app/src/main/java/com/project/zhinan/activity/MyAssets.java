package com.project.zhinan.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.project.zhinan.R;

public class MyAssets extends AppCompatActivity {

    private TextView mNoCrashTextView;
    private TextView mCurrentAccTextView;

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
        SharedPreferences sharedPreferences = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        int account = sharedPreferences.getInt("account", 0);
        if (account == 0) {
            mNoCrashTextView.setVisibility(View.VISIBLE);
        } else {
            mNoCrashTextView.setVisibility(View.GONE);
        }
        mCurrentAccTextView.setText(account + " 金币");
    }

    private void initView() {
        mNoCrashTextView = (TextView) findViewById(R.id.noCrash);
        mCurrentAccTextView = (TextView) findViewById(R.id.currentAcc);


    }
}
