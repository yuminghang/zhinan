package com.project.zhinan.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.project.zhinan.R;
import com.project.zhinan.dao.CollectionSqlliteHelper;
import com.project.zhinan.dao.HistorySqlliteHelper;

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
        CollectionSqlliteHelper collectionSqlliteHelper = new CollectionSqlliteHelper(AccountActivity.this);
        SQLiteDatabase writableDatabase = collectionSqlliteHelper.getWritableDatabase();
        writableDatabase.execSQL("delete from collection_table");
        writableDatabase.close();
        HistorySqlliteHelper historySqlliteHelper = new HistorySqlliteHelper(AccountActivity.this);
        SQLiteDatabase writableDatabase1 = historySqlliteHelper.getWritableDatabase();
        writableDatabase1.execSQL("delete from history_table");
        writableDatabase1.close();
        Toast.makeText(AccountActivity.this,"账户退出登录",Toast.LENGTH_SHORT).show();
        finish();
    }
}
