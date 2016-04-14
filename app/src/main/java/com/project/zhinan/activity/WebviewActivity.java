package com.project.zhinan.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

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
    private WebView mWebView1WebView;
    private Button mGetRmbButton;
    private ProgressBar mPbProgressBar;


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_layout);
        mGetRmbButton = (Button) findViewById(R.id.get_rmb);
        MyApplication.getInstance().addActivity(this);
        url = getIntent().getStringExtra("url");
        webview = (WebView) findViewById(R.id.webView1);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mGetRmbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WebviewActivity.this,"金币领取成功",Toast.LENGTH_SHORT).show();
            }
        });
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
        webview.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
//                if (i3-i2>0){
//                    mGetRmbButton.setVisibility(View.VISIBLE);
//                }else {
//                    mGetRmbButton.setVisibility(View.GONE);
//                }
                if (webview.getContentHeight() * webview.getScale() == (webview.getHeight() + webview.getScrollY())) {
                    mGetRmbButton.setVisibility(View.VISIBLE);
                    mGetRmbButton.setClickable(true);
                }else {
                    mGetRmbButton.setVisibility(View.GONE);
                    mGetRmbButton.setClickable(false);
                }
            }
        });
    }
}
