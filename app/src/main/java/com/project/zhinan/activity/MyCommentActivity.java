package com.project.zhinan.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.project.zhinan.MyApplication;
import com.project.zhinan.R;

public class MyCommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_comment);
    }

    public void send(View view) {
        Toast.makeText(this, "评论发送成功！请等待审核", Toast.LENGTH_SHORT).show();
        MyApplication.closeAllTvActivity();
        MyApplication.closeAllVideoActivity();
        MyApplication.closeAllNewsActivity();
        finish();
    }
}
