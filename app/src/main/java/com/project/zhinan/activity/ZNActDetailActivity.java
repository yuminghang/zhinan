package com.project.zhinan.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.project.zhinan.R;

public class ZNActDetailActivity extends AppCompatActivity {

    private TextView mTextViewTextView;
    private ImageView mDetailImageView;
    private Button btShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_znact_detail);
        mTextViewTextView = (TextView) findViewById(R.id.textView);
        mDetailImageView = (ImageView) findViewById(R.id.iv_detail);
        btShare = (Button) findViewById(R.id.bt_share);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        mTextViewTextView.setText(name);
        btShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
