package com.project.zhinan.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.project.zhinan.R;
import com.project.zhinan.net.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class paymentActivity extends Activity {

    private static final int SUCCESS = 1;
    private static final int ERROR = 2;
    private String order;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESS:
                    new AlertDialog.Builder(paymentActivity.this)
                            .setTitle("支付成功")
                            .setMessage("订单号码：" + order)
                            .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();
                    break;
                case ERROR:
                    MyToast("支付失败" + error);
                    break;
            }
        }
    };
    private String error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        order = intent.getStringExtra("order");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_payment);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.LinearLayout4);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(paymentActivity.this)
                        .setTitle("支付订单")
                        .setMessage("您将确定支付该订单")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //邓毅要做的事
                                sendPayPost();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });
    }

    private void sendPayPost() {
        try {
            HttpUtils.doGetAsynwithCookie("http://120.27.41.245:2888/pay?odernumber=" + order, this, new HttpUtils.CallBack() {
                @Override
                public void onRequestComplete(String result) {
                    if (result.contains("success")) {
                        //成功支付
                        handler.sendEmptyMessage(SUCCESS);
                    } else {
                        try {
                            JSONObject jsonObject1 = new JSONObject(result);
                            error = jsonObject1.getString("error");
                            handler.sendEmptyMessage(ERROR);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void MyToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
