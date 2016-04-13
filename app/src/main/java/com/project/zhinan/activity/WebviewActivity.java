package com.project.zhinan.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.project.zhinan.MyApplication;
import com.project.zhinan.R;

/**
 * Created by ymh on 2016/4/13.
 */
public class WebviewActivity extends Activity {
    private WebView webview;
    private String url, title;
    private String content;
    //    Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            content = msg.getData().getString("content");
////            spilt(content);
//            webview.loadDataWithBaseURL("", HttpUtils.getHtml(content), "text/html", "utf-8", "");
//            pb.setVisibility(View.INVISIBLE);
//        }
//    };//HttpUtils.getHtml(content)
    private SwipeRefreshLayout swipeRefreshLayout;
    private static ProgressBar pb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_layout);
        MyApplication.getInstance().addActivity(this);
        url = getIntent().getStringExtra("url");
        webview = (WebView) findViewById(R.id.webView1);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        pb = (ProgressBar) findViewById(R.id.pb);
        pb.setVisibility(View.VISIBLE);
//        HttpUtils.getData(url, handler);
//        webview.getSettings().setBuiltInZoomControls(true); //显示放大缩小 controler
//        webview.getSettings().setSupportZoom(true); //可以缩放
        webview.loadUrl(url);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                //结束
                pb.setVisibility(View.INVISIBLE);
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //开始
                super.onPageStarted(view, url, favicon);
            }
        });
    }
}
