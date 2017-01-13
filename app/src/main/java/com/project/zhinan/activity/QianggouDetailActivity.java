package com.project.zhinan.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;
import com.project.zhinan.MyApplication;
import com.project.zhinan.R;
import com.project.zhinan.base.fragment.BaseFragment;
import com.project.zhinan.dao.HistorySqlliteHelper;
import com.project.zhinan.view.VerificationCodeView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qianggou_detail);
        MyApplication.getInstance().addActivity(this);
        Intent intent = getIntent();//getIntent将该项目中包含的原始intent检索出来，将检索出来的intent赋值给一个Intent类型的变量intent
        Bundle bundle = intent.getExtras();//
        pos = bundle.getInt("pos");
        initVoice();
        content = "怕上火喝王老吉";
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
//                if (first) {
//                    first = false;
                Log.e("ssss", "点乐乐乐儿");
                //3.开始合成
                mTts.startSpeaking("小强小强小强小强小强小强小强", mSynListener);
//                }
                return false;
            }
        });
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

    private void initVoice() {
        //1.创建SpeechSynthesizer对象, 第二个参数：本地合成时传InitListener
        mTts = SpeechSynthesizer.createSynthesizer(QianggouDetailActivity.this, null);
        //2.合成参数设置，详见《科大讯飞MSC API手册(Android)》SpeechSynthesizer 类
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");//设置发音人
        mTts.setParameter(SpeechConstant.SPEED, "50");//设置语速
        mTts.setParameter(SpeechConstant.VOLUME, "80");//设置音量，范围0~100
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端
        //设置合成音频保存位置（可自定义保存位置），保存在“./sdcard/iflytek.pcm”
        //保存在SD卡需要在AndroidManifest.xml添加写SD卡权限
        //如果不需要保存合成音频，注释该行代码
//        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./sdcard/iflytek.pcm");
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
