package com.project.zhinan.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.project.zhinan.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class ZNActDetailActivity extends AppCompatActivity {

    private static final String TAG = "file";
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
        oks.setTitleUrl("http://123.206.84.242:2888/");
// text是分享文本，所有平台都需要这个字段
        oks.setText("欢迎下载指南App，参与该活动 Url:http://123.206.84.242:2888/");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath(Environment.getExternalStorageDirectory().getPath()+"/zhinan/qcoder.png");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://123.206.84.242:2888/");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://123.206.84.242:2888/");

// 启动分享GUI
        oks.show(this);
    }


    public static boolean copyAssetsFilesToData(Context context) {
        String inPath = "";
        String outPath = Environment.getExternalStorageDirectory().getPath()+"/zhinan";
        long begin = System.currentTimeMillis();
        boolean ret = copyFiles(context, inPath, outPath);
        long end = System.currentTimeMillis();
        Log.i(TAG, "copyAssetsFilesToData() elapsedTime:" + (end-begin));
        return ret;
    }

    /**
     * 从assets目录下拷贝整个文件夹，不管是文件夹还是文件都能拷贝
     *
     * @param context
     *            上下文
     * @param inPath
     *            文件目录，要拷贝的目录
     * @param outPath
     *            目标文件夹位置如：/sdcrad/mydir
     */
    public static boolean copyFiles(Context context, String inPath, String outPath) {
        Log.i(TAG, "copyFiles() inPath:" + inPath + ", outPath:" + outPath);
        String[] fileNames = null;
        try {// 获得Assets一共有几多文件
            fileNames = context.getAssets().list(inPath);
        } catch (IOException e1) {
            e1.printStackTrace();
            return false;
        }
        if (fileNames.length > 0) {//如果是目录
            File fileOutDir = new File(outPath);
            if(fileOutDir.isFile()){
                boolean ret = fileOutDir.delete();
                if(!ret){
                    Log.e(TAG, "delete() FAIL:" + fileOutDir.getAbsolutePath());
                }
            }
            if (!fileOutDir.exists()) { // 如果文件路径不存在
                if (!fileOutDir.mkdirs()) { // 创建文件夹
                    Log.e(TAG, "mkdirs() FAIL:" + fileOutDir.getAbsolutePath());
                    return false;
                }
            }
            for (String fileName : fileNames) { //递归调用复制文件夹
                String inDir = inPath;
                String outDir = outPath + File.separator;
                if(!inPath.equals("")) { //空目录特殊处理下
                    inDir = inDir + File.separator;
                }
                copyFiles(context,inDir + fileName, outDir + fileName);
            }
            return true;
        } else {//如果是文件
            try {
                File fileOut = new File(outPath);
                if(fileOut.exists()) {
                    boolean ret = fileOut.delete();
                    if(!ret){
                        Log.e(TAG, "delete() FAIL:" + fileOut.getAbsolutePath());
                    }
                }
                boolean ret = fileOut.createNewFile();
                if(!ret){
                    Log.e(TAG, "createNewFile() FAIL:" + fileOut.getAbsolutePath());
                }
                FileOutputStream fos = new FileOutputStream(fileOut);
                InputStream is = context.getAssets().open(inPath);
                byte[] buffer = new byte[1024];
                int byteCount=0;
                while((byteCount = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, byteCount);
                }
                fos.flush();//刷新缓冲区
                is.close();
                fos.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    @Override
    protected void onResume() {
        copyAssetsFilesToData(this);
        super.onResume();
    }
}
