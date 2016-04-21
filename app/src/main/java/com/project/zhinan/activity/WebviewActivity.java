package com.project.zhinan.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.project.zhinan.MyApplication;
import com.project.zhinan.R;
import com.project.zhinan.dao.HistorySqlliteHelper;
import com.project.zhinan.view.ScrollWebView;

/**
 * Created by ymh on 2016/4/13.
 */
public class WebviewActivity extends Activity {
    private ScrollWebView webview;
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
    private ProgressBar pb;
    private Button mGetRmbButton;
    private SharedPreferences sharedPreferences;
    private boolean isLogin;
    private int rbs;
    private int flag = 0;


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_layout);
        mGetRmbButton = (Button) findViewById(R.id.get_rmb);
        MyApplication.getInstance().addActivity(this);
        url = getIntent().getStringExtra("url");
        webview = (ScrollWebView) findViewById(R.id.webView1);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mGetRmbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag == 0) {
                    Toast.makeText(WebviewActivity.this, "请先看完广告再领金币。。", Toast.LENGTH_SHORT).show();
                    return;
                }
                sharedPreferences = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
                isLogin = sharedPreferences.getBoolean("isLogin", false);
                if (isLogin) {
                    rbs = 1;
                    AddHistory();
                } else {
                    Toast.makeText(WebviewActivity.this, "您还未登陆。。。", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(WebviewActivity.this, LoginActivity.class));
                }
            }
        });
        pb = (ProgressBar) findViewById(R.id.pb);
        pb.setVisibility(View.VISIBLE);
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
        webview.setOnScrollChangedCallback(new ScrollWebView.OnScrollChangedCallback() {
            @Override
            public void onScroll(int dx, int dy) {
                if (dy > 0) {
//                    System.out.println("----------上滑-------------");
                    if (webview.getContentHeight() * webview.getScale() == (webview.getHeight() + webview.getScrollY())) {
                        flag = 1;
                    } else {
                        flag = 0;
                    }
                } else {
                    flag = 0;
//                    System.out.println("----------下滑-------------");
                }
            }
        });

    }

    private void AddHistory() {
        /**
         * 增加领取记录
         */
        String adID = "12313132";
        HistorySqlliteHelper historySqlliteHelper = new HistorySqlliteHelper(WebviewActivity.this);
        SQLiteDatabase writableDatabase = historySqlliteHelper.getWritableDatabase();
        Cursor cursor = writableDatabase.rawQuery("select * from history_table where ad_id= " + adID, null);
        if (cursor.getCount() > 0) {
            Toast.makeText(WebviewActivity.this, "已经领取过了", Toast.LENGTH_SHORT).show();
            return;
        }
        ContentValues initialValues = new ContentValues();
        initialValues.put("ad_id", adID);
        initialValues.put("rbs", rbs);
        initialValues.put("ad_url", url);
        initialValues.put("if_get", 1);
        Toast.makeText(WebviewActivity.this, "领取成功", Toast.LENGTH_SHORT).show();
        writableDatabase.insert("history_table", null, initialValues);
        writableDatabase.close();
    }

    public void addRMB(int rmbs) {
        try {
            sharedPreferences = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
            isLogin = sharedPreferences.getBoolean("isLogin", false);
            if (isLogin) {
                int account = sharedPreferences.getInt("account", 0);
                account = account + rmbs;
                sharedPreferences.edit().putInt("account", account).commit();
            }
        } catch (Exception e) {
            Log.d("加钱错误", e.toString());
        }
    }
}
