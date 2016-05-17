package com.project.zhinan.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.zhinan.R;

public class ShangQuanActivity extends AppCompatActivity {

    private ImageView mBackImageView;
    private TextView mNameTextView;
    private WebView mMoreInfoWebView;
    private String imgUrl;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shang_quan);
        Intent intent = getIntent();
        imgUrl = intent.getStringExtra("imgUrl");
        name = intent.getStringExtra("name");
        mBackImageView = (ImageView) findViewById(R.id.iv_back);
        mNameTextView = (TextView) findViewById(R.id.tv_name);
        mMoreInfoWebView = (WebView) findViewById(R.id.wb_more_info);
        WebSettings settings = mMoreInfoWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mMoreInfoWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                return false;

            }

        });

        mMoreInfoWebView.loadUrl("http://coupon.m.jd.com/seckill/seckillList?wareId=2610963");

    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onResume() {
        super.onResume();
        Glide.with(this).load(imgUrl).into(mBackImageView);
        mNameTextView.setText(name);

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
