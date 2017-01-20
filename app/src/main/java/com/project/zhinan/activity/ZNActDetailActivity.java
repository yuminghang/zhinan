package com.project.zhinan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.zhinan.R;
import com.project.zhinan.api.Urls;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class ZNActDetailActivity extends AppCompatActivity {

    private TextView mTextViewTextView;
    private ImageView mDetailImageView;
    private Button btShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShareSDK.initSDK(this);
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
                showShare();
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
        oks.setText("欢迎下载指南App，参与该活动 Url:" + Urls.BaseUrl);
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


    @Override
    protected void onResume() {

        super.onResume();
    }
}
