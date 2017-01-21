package com.project.zhinan.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.loader.GlideImageLoader;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.project.zhinan.MyApplication;
import com.project.zhinan.R;
import com.project.zhinan.activity.LoginActivity;
import com.project.zhinan.bean.FBBean;
import com.project.zhinan.utils.ConstantValue;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.tencent.upload.Const;
import com.tencent.upload.UploadManager;
import com.tencent.upload.task.ITask;
import com.tencent.upload.task.IUploadTaskListener;
import com.tencent.upload.task.data.FileInfo;
import com.tencent.upload.task.impl.PhotoUploadTask;
import com.vincent.filepicker.Constant;
import com.vincent.filepicker.activity.ImagePickActivity;
import com.vincent.filepicker.filter.entity.ImageFile;
import com.vincent.filepicker.filter.entity.VideoFile;

import org.feezu.liuli.timeselector.TimeSelector;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

import static android.app.Activity.RESULT_OK;
import static com.vincent.filepicker.Util.getScreenWidth;
import static com.vincent.filepicker.activity.ImagePickActivity.IS_NEED_CAMERA;

/**
 * Created by ymh on 2016/3/11.
 */
public class FabuFragment extends Fragment implements View.OnClickListener {


    private static final int IMAGECHANGED = 1;
    private static final int MAXIMGNUM = 5;
    private static final int IMAGEUPLOADED = 2;
    private static final int UPLOADSUCCESS = 3;
    private static final int UPLOADFAIL = 4;
    private static final int JSONERROR = 5;
    private static final int NETERROR = 6;
    private EditText mAdNameEditText;
    private LinearLayout mImgContainerLinearLayout;
    private ImageButton mAddImgImageButton;
    private EditText mAdKeyEditText;
    private TextView mStartTimeTextView;
    private TextView mEndTimeTextView;
    private EditText mBudgetEditText;
    private EditText mSigMoneyEditText;
    private CheckBox mEduCheckBox;
    private CheckBox mTourCheckBox;
    private CheckBox mBuildCheckBox;
    private CheckBox mMedCheckBox;
    private CheckBox mFoodCheckBox;
    private Button mFabuButton;
    private LinearLayout mEditLinearLayout;
    private ScrollView mEditScrollView;
    public String starttime;
    public ArrayList<String> imgList;
    private Handler handler;
    private Button mClearImg;
    private LinearLayout mTime;
    private String mAdname;
    private String mKeywords;
    private String mAdStart;
    private String mAdEnd;
    private String mAdBuget;
    private String mAdSigleMoney;
    private ArrayList<String> UploadimgUrls;
    private ArrayList<String> mCheckList;
    private ProgressBar mProgress;
    HashMap<String, String> stringHashMap = new HashMap<>();
    private EditText mAdReadEditText;
    private String mAdRead;
    private CheckBox mPropertyCheckBox;
    private CheckBox mRecruitmentCheckBox;
    private CheckBox mCarCheckBox;
    private CheckBox mFinancialCheckBox;
    private CheckBox mCommuicationCheckBox;
    private CheckBox mMakeupsCheckBox;
    private CheckBox mServiceCheckBox;
    private CheckBox mPublicWelfareCheckBox;
    private CheckBox[] cbs;

    {
        stringHashMap.put("教育", "edu");
        stringHashMap.put("旅游", "tour");
        stringHashMap.put("房产", "property");
        stringHashMap.put("建材", "build");
        stringHashMap.put("医药", "med");
        stringHashMap.put("餐饮", "food");
        stringHashMap.put("招聘", "recruitment");
        stringHashMap.put("汽车", "car");
        stringHashMap.put("金融", "financial");
        stringHashMap.put("通信", "commuication");
        stringHashMap.put("美妆", "makeups");
        stringHashMap.put("服务", "service");
        stringHashMap.put("公益", "public_welfare");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fabufragment_layout, container, false);
        mPropertyCheckBox = (CheckBox) view.findViewById(R.id.cb_property);
        mRecruitmentCheckBox = (CheckBox) view.findViewById(R.id.cb_recruitment);
        mCarCheckBox = (CheckBox) view.findViewById(R.id.cb_car);
        mFinancialCheckBox = (CheckBox) view.findViewById(R.id.cb_financial);
        mCommuicationCheckBox = (CheckBox) view.findViewById(R.id.cb_commuication);
        mMakeupsCheckBox = (CheckBox) view.findViewById(R.id.cb_makeups);
        mServiceCheckBox = (CheckBox) view.findViewById(R.id.cb_service);
        mPublicWelfareCheckBox = (CheckBox) view.findViewById(R.id.cb_public_welfare);
        mAdNameEditText = (EditText) view.findViewById(R.id.et_ad_name);
        mImgContainerLinearLayout = (LinearLayout) view.findViewById(R.id.img_container);
        mAddImgImageButton = (ImageButton) view.findViewById(R.id.add_img);
        mAddImgImageButton.setOnClickListener(this);
        mAdKeyEditText = (EditText) view.findViewById(R.id.et_ad_key);
        mAdReadEditText = (EditText) view.findViewById(R.id.et_ad_read);
        mStartTimeTextView = (TextView) view.findViewById(R.id.start_time);
        mStartTimeTextView.setOnClickListener(this);
        mEndTimeTextView = (TextView) view.findViewById(R.id.end_time);
        mEndTimeTextView.setOnClickListener(this);
        mBudgetEditText = (EditText) view.findViewById(R.id.budget);
        mSigMoneyEditText = (EditText) view.findViewById(R.id.sig_money);
        mEduCheckBox = (CheckBox) view.findViewById(R.id.cb_edu);
        mTourCheckBox = (CheckBox) view.findViewById(R.id.cb_tour);
        mBuildCheckBox = (CheckBox) view.findViewById(R.id.cb_build);
        mMedCheckBox = (CheckBox) view.findViewById(R.id.cb_med);
        mFoodCheckBox = (CheckBox) view.findViewById(R.id.cb_food);
        mFabuButton = (Button) view.findViewById(R.id.bt_fabu);
        mFabuButton.setOnClickListener(this);
        mEditLinearLayout = (LinearLayout) view.findViewById(R.id.ll_edit);
        mEditScrollView = (ScrollView) view.findViewById(R.id.sv_edit);
        mClearImg = (Button) view.findViewById(R.id.clear_img);
        mClearImg.setOnClickListener(this);
        mTime = (LinearLayout) view.findViewById(R.id.time);
        mProgress = (ProgressBar) view.findViewById(R.id.fabu_progress);
        imgList = new ArrayList<>();
        UploadimgUrls = new ArrayList<>();
        initData();
        return view;
    }

    private View addView(String path) {
        // TODO 动态添加布局(xml方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LayoutInflater inflater3 = LayoutInflater.from(getContext());
        View view = inflater3.inflate(R.layout.public_img_item, null);
        ImageView imageImageView = (ImageView) view.findViewById(R.id.image);
        Glide.with(getContext()).load(path).into(imageImageView);

        view.setLayoutParams(lp);
        return view;

    }


    @Override
    public void onResume() {

        initHandler();
        super.onResume();
    }

    private void initHandler() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case IMAGECHANGED:
                        mImgContainerLinearLayout.removeAllViews();
                        for (String path :
                                imgList) {
                            mImgContainerLinearLayout.addView(addView(path));
                        }
                        break;
                    case IMAGEUPLOADED:
                        infoupload();
                        break;
                    case UPLOADSUCCESS:
                        toast("上传成功");
                        dissMissProgress();
                        initData();
                        break;
                    case UPLOADFAIL:
                        toast("上传失败");
                        dissMissProgress();
                        break;
                    case JSONERROR:
                        toast("JSON解析错误");
                        dissMissProgress();
                        break;
                    case NETERROR:
                        toast("网络错误");
                        dissMissProgress();
                        break;
                    default:
                        dissMissProgress();
                        break;
                }
            }
        };
    }

    private void dissMissProgress() {
        mProgress.setVisibility(View.GONE);
    }

    private void showProgress() {
        mProgress.setVisibility(View.VISIBLE);
    }


    private void initData() {
        mProgress.setVisibility(View.GONE);
        mAdNameEditText.setText("");
        mImgContainerLinearLayout.removeAllViews();
        mAdKeyEditText.setText("");
        mStartTimeTextView.setText("");
        mStartTimeTextView.setHint("开始时间");
        mEndTimeTextView.setHint("结束时间");
        mEndTimeTextView.setText("");
        mBudgetEditText.setText("");
        mSigMoneyEditText.setText("");
        mAdReadEditText.setText("");
        cbs = new CheckBox[]{mEduCheckBox,
                mTourCheckBox,
                mBuildCheckBox,
                mMedCheckBox,
                mFoodCheckBox,
                mPropertyCheckBox,
                mRecruitmentCheckBox,
                mCarCheckBox,
                mFinancialCheckBox,
                mCommuicationCheckBox,
                mMakeupsCheckBox,
                mServiceCheckBox,
                mPublicWelfareCheckBox};
        for (CheckBox cb : cbs) {
            cb.setChecked(false);
        }
    }

    @Override
    public void onDestroy() {
        handler = null;
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_img:
                if (MAXIMGNUM - imgList.size() > 0) {
                    Intent intent1 = new Intent(getActivity(), ImagePickActivity.class);
                    intent1.putExtra(IS_NEED_CAMERA, true);
                    intent1.putExtra(Constant.MAX_NUMBER, MAXIMGNUM - imgList.size());
                    startActivityForResult(intent1, Constant.REQUEST_CODE_PICK_IMAGE);
                } else {
                    Toast.makeText(getContext(), "最多上传" + MAXIMGNUM + "张图片", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.start_time:
                TimeSelector timeSelector = new TimeSelector(getContext(), new TimeSelector.ResultHandler() {

                    @Override
                    public void handle(String time) {
                        starttime = time;
                        mStartTimeTextView.setText(time);
//                        Toast.makeText(getContext(), time, Toast.LENGTH_LONG).show();
                    }
                }, getFormatStartDate(), getFormatEndDate());
                timeSelector.show();
                break;
            case R.id.end_time:
                if (starttime == null) {
                    Toast.makeText(getContext(), "请先选择开始时间", Toast.LENGTH_LONG).show();
                } else {
                    TimeSelector timeSelectorend = new TimeSelector(getContext(), new TimeSelector.ResultHandler() {

                        @Override
                        public void handle(String time) {
                            mEndTimeTextView.setText(time);
//                        Toast.makeText(getContext(), time, Toast.LENGTH_LONG).show();
                        }
                    }, getFormatStartDate(), getFormatEndDate());
                    timeSelectorend.show();
                }
                break;
            case R.id.clear_img:
                imgList.clear();
                UploadimgUrls.clear();
                mImgContainerLinearLayout.removeAllViews();
                break;
            case R.id.bt_fabu:
                if (isLogin() && infoCheck()) {
                    uploadfile();
                }
                break;
        }
    }

    private void infoupload() {
        new Thread() {
            @Override
            public void run() {
                SharedPreferences cookie = getActivity().getSharedPreferences("cookie", Context.MODE_PRIVATE);
                final String my_cookie = cookie.getString("my_cookie", "");
                FBBean fbBean = new FBBean();
                fbBean.setName(mAdname);
                fbBean.setAd_put_begintime(mAdStart);
                fbBean.setAd_put_endtime(mAdEnd);
                fbBean.setBudget(mAdBuget);
                fbBean.setSig_money(mAdSigleMoney);
                fbBean.setImgurls(UploadimgUrls);
                fbBean.setKey(mKeywords);
                fbBean.setTags(mCheckList);
                fbBean.setRead(mAdRead);
                OkHttpClient client = new OkHttpClient();
                MediaType mediaType = MediaType.parse("application/json");
                Gson gson = new Gson();
                RequestBody body = RequestBody.create(mediaType, gson.toJson(fbBean));
                Request request = new Request.Builder()
                        .url("http://120.27.41.245:2888/mpostNew")
                        .post(body)
                        .addHeader("content-type", "application/json")
                        .addHeader("cache-control", "no-cache")
                        .addHeader("cookie", my_cookie)
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    String string = response.body().string();
                    JSONObject jsonObject = new JSONObject(string);
                    final String orderno = jsonObject.getString("orderno");
                    System.out.println(string);
                    if (string.contains("success")) {
                        new Thread() {
                            @Override
                            public void run() {
                                OkHttpClient client = new OkHttpClient();
                                MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                                RequestBody body = RequestBody.create(mediaType, "adorder=" + orderno +
                                        "&lat=" + MyApplication.latitude +
                                        "&lon=" + MyApplication.longitude);
                                Request request = new Request.Builder()
                                        .url("http://120.27.41.245:2888/adposition")
                                        .post(body)
                                        .addHeader("content-type", "application/x-www-form-urlencoded")
                                        .addHeader("cache-control", "no-cache")
                                        .addHeader("cookie", my_cookie)
                                        .build();

                                try {
                                    Response response = client.newCall(request).execute();
                                    System.out.println(response.body().string());
                                    handler.sendEmptyMessage(UPLOADSUCCESS);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                    } else {
                        handler.sendEmptyMessage(UPLOADFAIL);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    handler.sendEmptyMessage(NETERROR);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }


    private boolean infoCheck() {
        mAdname = mAdNameEditText.getText().toString().trim();
        mKeywords = mAdKeyEditText.getText().toString().trim();
        mAdRead = mAdReadEditText.getText().toString().trim();
        mAdStart = mStartTimeTextView.getText().toString().trim();
        mAdEnd = mEndTimeTextView.getText().toString().trim();
        mAdBuget = mBudgetEditText.getText().toString().trim();
        mAdSigleMoney = mSigMoneyEditText.getText().toString().trim();
        return isNameValid(mAdname) && isKeywordsValid(mKeywords) && isReadValid(mAdRead) && isTimeValid(mAdStart, mAdEnd) && isBugetValid(mAdBuget) && isSigleMoneyValid(mAdSigleMoney) && isCkValid();
    }

    private boolean isReadValid(String s) {
        if (s.length() >= 4 && s.length() <= 30)
            return true;
        mAdReadEditText.requestFocus();
        toast("朗读文本长度错误,请控制在4～30个字");
        return false;
    }

    private boolean isCkValid() {

        mCheckList = new ArrayList<String>();
        for (CheckBox cb :cbs) {
            if (cb.isChecked()) {
                mCheckList.add(stringHashMap.get(cb.getText().toString()));
            }
        }
        if (mCheckList.size() < 1) {
            toast("请至少选择一个分类");
            return false;
        }
        if (mCheckList.size() > 3) {
            toast("最多只能选三个分类");
            return false;
        }
        return true;
    }

    private boolean isSigleMoneyValid(String mAdSigleMoney) {
        if (isInteger(mAdSigleMoney)) {
            if (Integer.parseInt(mAdSigleMoney) < 0) {
                mSigMoneyEditText.requestFocus();
                toast("请输入大于零的整数");
                return false;
            } else {
                return true;
            }
        }
        mSigMoneyEditText.requestFocus();
        toast("请输入整数");
        return false;
    }

    private boolean isBugetValid(String mAdBuget) {
        if (isInteger(mAdBuget)) {
            if (Integer.parseInt(mAdBuget) < 0) {
                mBudgetEditText.requestFocus();
                toast("请输入大于零的整数");
                return false;
            } else {
                return true;
            }
        }
        mBudgetEditText.requestFocus();
        toast("请输入整数");
        return false;

    }

    private boolean isInteger(String str) {
        if (null == str || "".equals(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    private boolean isTimeValid(String mAdStart, String mAdEnd) {
        if (mAdStart.length() > 0 && mAdEnd.length() > 0)
            return true;
        Toast.makeText(getContext(), "请选择正确的时间", Toast.LENGTH_SHORT).show();
        return false;
    }

    private boolean isKeywordsValid(String mKeywords) {
        if (mKeywords.length() >= 4 && mKeywords.length() <= 10)
            return true;
        mAdKeyEditText.requestFocus();
        toast("关键词长度错误");
        return false;
    }

    private boolean isNameValid(String mAdname) {
        if (mAdname.length() > 0)
            return true;
        mAdNameEditText.requestFocus();
        toast("广告名称未填写");
        return false;
    }

    private void toast(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    private boolean isLogin() {
        if (!MyApplication.getInstance().isLogin()) toast("还未登陆");

        return MyApplication.getInstance().isLogin();
    }

    public String getFormatStartDate() {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateStr = sdf.format(now.getTimeInMillis());
        System.out.println(dateStr);
        return dateStr;
    }

    public String getFormatEndDate() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MONTH, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateStr = sdf.format(now.getTimeInMillis());
        return dateStr;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constant.REQUEST_CODE_PICK_IMAGE:
                if (resultCode == RESULT_OK) {
                    ArrayList<ImageFile> list = data.getParcelableArrayListExtra(Constant.RESULT_PICK_IMAGE);
                    mImgContainerLinearLayout.removeAllViews();
                    for (ImageFile file : list) {
                        String path = file.getPath();
                        imgList.add(path);
                    }
                    for (String path :
                            imgList) {
                        mImgContainerLinearLayout.addView(addView(path));
                    }
                }
                break;
        }
    }

    public void uploadfile() {
        showProgress();
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < imgList.size(); i++) {
            final int finalI = i;
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    UploadManager guide = new UploadManager(getContext(), "10035266", Const.FileType.Photo, null);
                    PhotoUploadTask uploadTask = new PhotoUploadTask(imgList.get(finalI), new IUploadTaskListener() {
                        @Override
                        public void onUploadSucceed(FileInfo fileInfo) {
                            System.out.println("----------------" + fileInfo.toString() + "--------" + fileInfo.url);
                            UploadimgUrls.add(fileInfo.url);
                            if (finalI == imgList.size() - 1) {
                                handler.sendEmptyMessage(IMAGEUPLOADED);
                            }
                        }

                        @Override
                        public void onUploadFailed(int i, String s) {
                            System.out.println("----------------" + s);
                        }

                        @Override
                        public void onUploadProgress(long l, long l1) {
                            System.out.println(l + "----------------" + l1);
                        }

                        @Override
                        public void onUploadStateChange(ITask.TaskState taskState) {
                            System.out.println("----------------" + taskState);
                        }
                    });
                    OkHttpClient client = new OkHttpClient();

                    Request request = new Request.Builder()
                            .url("http://120.27.41.245:2888/tx/img")
                            .get()
                            .addHeader("cache-control", "no-cache")
                            .build();
                    try {
                        Response response = client.newCall(request).execute();
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        System.out.println(jsonObject.getString("url") + "========================================");
                        uploadTask.setBucket("guide");
                        uploadTask.setFileId("guide" + UUID.randomUUID());
                        uploadTask.setAuth(jsonObject.getString("sign"));
                        guide.upload(uploadTask);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
