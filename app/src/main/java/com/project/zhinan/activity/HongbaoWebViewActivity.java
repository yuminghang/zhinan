package com.project.zhinan.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.project.zhinan.R;

public class HongbaoWebViewActivity extends AppCompatActivity {


    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hongbao_web_view);
//        http://m.1.163.com/detail/intro/00-08-97.html?ter=yixin
//        webView = (WebView) findViewById(R.id.webView);
//        webView.loadUrl("http://m.1.163.com/detail/intro/00-08-97.html?ter=yixin");
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HongbaoWebViewActivity.this, paymentActivity.class));
            }
        });
    }
}
