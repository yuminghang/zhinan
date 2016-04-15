package com.project.zhinan.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.project.zhinan.R;

public class AccountActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView mTitlebarTextView;
    private TextView mTvchangepassTextView;
    private Button mUnloginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        initView();
    }

    private void initView() {
        mTvchangepassTextView = (TextView) findViewById(R.id.tvchangepass);
        mUnloginButton = (Button) findViewById(R.id.bt_unlogin);
        mTvchangepassTextView.setOnClickListener(this);
        mUnloginButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvchangepass:
                startActivity(new Intent(AccountActivity.this,ChangePassActivity.class));
                finish();
                break;
            case R.id.bt_unlogin:
                unLogin();
                break;
            default:
                break;
        }
    }

    private void unLogin() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.clear();
        edit.commit();
        Toast.makeText(AccountActivity.this,"账户退出登录",Toast.LENGTH_SHORT).show();
        finish();
    }
}
