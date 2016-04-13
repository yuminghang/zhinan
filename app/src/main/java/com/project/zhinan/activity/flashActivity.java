package com.project.zhinan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.project.zhinan.MyApplication;
import com.project.zhinan.R;

public class FlashActivity extends Activity {

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//            startActivity(new Intent(FlashActivity.this, MainActivity.class));
        }
    };
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        MyApplication.getInstance().addActivity(this);
        setContentView(R.layout.flashactivity);
        runnable = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(FlashActivity.this, MainActivity.class));
            }
        };
        handler.postDelayed(runnable, 3000);
    }

    public void click(View view) {
        handler.removeCallbacks(runnable);
        startActivity(new Intent(FlashActivity.this, MainActivity.class));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            MyApplication.getInstance().exit();
        }
        return super.onKeyDown(keyCode, event);
    }
}
