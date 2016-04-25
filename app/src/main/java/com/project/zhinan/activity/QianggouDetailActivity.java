package com.project.zhinan.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.zhinan.MyApplication;
import com.project.zhinan.R;
import com.project.zhinan.base.fragment.BaseFragment;
import com.project.zhinan.dao.HistorySqlliteHelper;
import com.project.zhinan.view.MyPopupWindow;

public class QianggouDetailActivity extends Activity {
    private int pos;
    private Button mGetRmbButton;
    private SharedPreferences sharedPreferences;
    private boolean isLogin;
    private int rbs;
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qianggou_detail);
        MyApplication.getInstance().addActivity(this);
        Intent intent = getIntent();//getIntent将该项目中包含的原始intent检索出来，将检索出来的intent赋值给一个Intent类型的变量intent
        Bundle bundle = intent.getExtras();//
        pos = bundle.getInt("pos");
        et = (EditText) findViewById(R.id.et);
        mGetRmbButton = (Button) findViewById(R.id.get_rmb);
        mGetRmbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
                isLogin = sharedPreferences.getBoolean("isLogin", false);
                if (!isLogin) {
                    Toast.makeText(QianggouDetailActivity.this, "您还未登陆。。。", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(QianggouDetailActivity.this, LoginActivity.class));
                } else {
                    rbs = 1;
                    BaseFragment.lastRead = pos;
                    BaseFragment.isRead[pos] = 1;
                    if (TextUtils.equals(et.getText().toString(), "怕上火喝王老吉")) {
                        Toast.makeText(QianggouDetailActivity.this, "领取成功！", Toast.LENGTH_SHORT).show();
                        AddHistory();
                        finish();
                    } else {
                        Toast.makeText(QianggouDetailActivity.this, "关键词错误", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void AddHistory() {
        /**
         * 增加领取记录
         */
        String adID = "12313132";
        HistorySqlliteHelper historySqlliteHelper = new HistorySqlliteHelper(QianggouDetailActivity.this);
        SQLiteDatabase writableDatabase = historySqlliteHelper.getWritableDatabase();
        Cursor cursor = writableDatabase.rawQuery("select * from history_table where ad_id= " + adID, null);
        if (cursor.getCount() > 0) {
            Toast.makeText(QianggouDetailActivity.this, "已经领取过了", Toast.LENGTH_SHORT).show();
            return;
        }
        ContentValues initialValues = new ContentValues();
        initialValues.put("ad_id", adID);
        initialValues.put("rbs", rbs);
//        initialValues.put("ad_url", url);
        initialValues.put("if_get", 1);
        Toast.makeText(QianggouDetailActivity.this, "领取成功", Toast.LENGTH_SHORT).show();
        writableDatabase.insert("history_table", null, initialValues);
        writableDatabase.close();
    }

}
