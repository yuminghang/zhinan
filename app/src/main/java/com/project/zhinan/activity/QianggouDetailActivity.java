package com.project.zhinan.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;
import com.project.zhinan.MyApplication;
import com.project.zhinan.R;
import com.project.zhinan.api.Urls;
import com.project.zhinan.base.fragment.BaseFragment;
import com.project.zhinan.bean.returnBean;
import com.project.zhinan.dao.HistorySqlliteHelper;
import com.project.zhinan.view.VerificationCodeView;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class QianggouDetailActivity extends Activity {
    private static boolean first = true;
    private int pos;
    private Button mGetRmbButton;
    private SharedPreferences sharedPreferences;
    private boolean isLogin;
    private int rbs;
    private EditText et;
    //合成监听器
    private SynthesizerListener mSynListener = new SynthesizerListener() {
        //会话结束回调接口，没有错误时，error为null
        public void onCompleted(SpeechError error) {
        }

        //缓冲进度回调
        //percent为缓冲进度0~100，beginPos为缓冲音频在文本中开始位置，endPos表示缓冲音频在文本中结束位置，info为附加信息。
        public void onBufferProgress(int percent, int beginPos, int endPos, String info) {
        }

        //开始播放
        public void onSpeakBegin() {
        }

        //暂停播放
        public void onSpeakPaused() {
        }

        //播放进度回调
        //percent为播放进度0~100,beginPos为播放音频在文本中开始位置，endPos表示播放音频在文本中结束位置.
        public void onSpeakProgress(int percent, int beginPos, int endPos) {
        }

        //恢复播放回调接口
        public void onSpeakResumed() {
        }

        //会话事件回调接口
        public void onEvent(int arg0, int arg1, int arg2, Bundle arg3) {
        }
    };
    private SpeechSynthesizer mTts;
    private VerificationCodeView iv1;
    private String content;
    private String readContent;
    private LinearLayout image_container;
    private ImageView back;
    private String uuid;
    private Response response;
    private ImageView share;
    private String returnData;
    private int sigmoney;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    et.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    Toast.makeText(QianggouDetailActivity.this, "领取成功！", Toast.LENGTH_SHORT).show();
                    addJiFen();
//                    AddHistory();
                    finish();
                    break;
                case 2:
                    Gson gson = new Gson();
                    returnBean returnBean = gson.fromJson(returnData, com.project.zhinan.bean.returnBean.class);
                    Toast.makeText(QianggouDetailActivity.this, returnBean.getError(), Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };
    private LinearLayout show_LinearLayout;
    private Button submit_rmb;
    private ScrollView scrollView;

    private void addJiFen() {
        sharedPreferences = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        int account = sharedPreferences.getInt("account", 0);
        sharedPreferences.edit().putInt("account", account + sigmoney).apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qianggou_detail);
        MyApplication.getInstance().addActivity(this);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        show_LinearLayout = (LinearLayout) findViewById(R.id.show_LinearLayout);
        image_container = (LinearLayout) findViewById(R.id.image_container);//动态添加view尚未父View
        Intent intent = getIntent();//getIntent将该项目中包含的原始intent检索出来，将检索出来的intent赋值给一个Intent类型的变量intent
        Bundle bundle = intent.getExtras();//
        pos = bundle.getInt("pos");
        content = intent.getStringExtra("key");
        readContent = intent.getStringExtra("read");
        uuid = intent.getStringExtra("uuid");
        sigmoney = Integer.parseInt(intent.getStringExtra("sigmoney"));
        String[] img_urls = bundle.getStringArray("img_urls");
        initImg(img_urls);
        initVoice();

        back = (ImageView) findViewById(R.id.back);
        share = (ImageView) findViewById(R.id.share);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });
        iv1 = (VerificationCodeView) findViewById(R.id.iv1);
        iv1.setvCode(content);
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv1.refreshCode();
            }
        });
        et = (EditText) findViewById(R.id.et);
        et.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mTts.startSpeaking(readContent, mSynListener);
                return false;
            }
        });
        mGetRmbButton = (Button) findViewById(R.id.get_rmb);
        mGetRmbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_LinearLayout.setVisibility(View.VISIBLE);
                et.setVisibility(View.VISIBLE);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                }, 500);

            }
        });
        submit_rmb = (Button) findViewById(R.id.submit_rmb);
        submit_rmb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
                isLogin = sharedPreferences.getBoolean("isLogin", false);
                if (!isLogin) {
                    Toast.makeText(QianggouDetailActivity.this, "您还未登陆。。。", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(QianggouDetailActivity.this, LoginActivity.class));
                } else {
                    rbs = 1;
                    BaseFragment.lastRead = pos;
                    BaseFragment.isRead[pos] = 1;
                    if (TextUtils.equals(et.getText().toString().trim(), content)) {
                        new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                sendData();
                            }
                        }.start();
                    } else {
                        Toast.makeText(QianggouDetailActivity.this, "核心记忆词错误", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("指南");
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(Urls.BaseUrl);
// text是分享文本，所有平台都需要这个字段
        oks.setText("欢迎下载指南App，看广告领红包！ 链接:" + Urls.BaseUrl);
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath(Environment.getExternalStorageDirectory().getPath() + "/zhinan/qcoder.png");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(Urls.BaseUrl);
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(Urls.BaseUrl);
// 启动分享GUI
        oks.show(this);
    }

    private void sendData() {
        SharedPreferences cookie = QianggouDetailActivity.this.getSharedPreferences("cookie", Context.MODE_PRIVATE);
        final String my_cookie = cookie.getString("my_cookie", "");
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "ad_uuid=" + uuid);
        Request request = new Request.Builder()
                .url("http://120.27.41.245:2888/collectpoints")
                .post(body)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .addHeader("cache-control", "no-cache")
                .addHeader("cookie", my_cookie)
                .addHeader("postman-token", "8ecc84ca-f107-0bdf-9d0f-c6c81589e453")
                .build();
        Response response = null;

        try {
            response = client.newCall(request).execute();
            returnData = response.body().string();
            if (returnData.contains("success")) {
                handler.sendEmptyMessage(1);//成功
            } else {
                handler.sendEmptyMessage(2);//失败
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initImg(String[] img_urls) {
        for (int i = 0; i < img_urls.length; i++) {
            //定义子View中两个元素的布局
            ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            ImageView iv = new ImageView(this);
            iv.setLayoutParams(vlp);//设置TextView的布局
            iv.setPadding(0, 0, 0, 0);//设置边距
            image_container.addView(iv);//将TextView 添加到子View 中
            Glide.with(QianggouDetailActivity.this).load(img_urls[i]).into(iv);
        }
        handler.sendEmptyMessageDelayed(0, 500);
    }

    private void initVoice() {
        //1.创建SpeechSynthesizer对象, 第二个参数：本地合成时传InitListener
        mTts = SpeechSynthesizer.createSynthesizer(QianggouDetailActivity.this, null);
        //2.合成参数设置，详见《科大讯飞MSC API手册(Android)》SpeechSynthesizer 类
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");//设置发音人
        mTts.setParameter(SpeechConstant.SPEED, "50");//设置语速
        mTts.setParameter(SpeechConstant.VOLUME, "80");//设置音量，范围0~100
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端
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
