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
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.loader.GlideImageLoader;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.project.zhinan.R;
import com.project.zhinan.activity.LoginActivity;
import com.project.zhinan.utils.ConstantValue;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
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
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.vincent.filepicker.Util.getScreenWidth;
import static com.vincent.filepicker.activity.ImagePickActivity.IS_NEED_CAMERA;

/**
 * Created by ymh on 2016/3/11.
 */
public class FabuFragment extends Fragment implements View.OnClickListener {


    private static final int IMAGECHANGED = 1;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fabufragment_layout, container, false);
        mAdNameEditText = (EditText) view.findViewById(R.id.et_ad_name);
        mImgContainerLinearLayout = (LinearLayout) view.findViewById(R.id.img_container);
        mAddImgImageButton = (ImageButton) view.findViewById(R.id.add_img);
        mAddImgImageButton.setOnClickListener(this);
        mAdKeyEditText = (EditText) view.findViewById(R.id.et_ad_key);
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

        imgList=new ArrayList<>();
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
                }
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_img:
                Intent intent1 = new Intent(getActivity(), ImagePickActivity.class);
                intent1.putExtra(IS_NEED_CAMERA, true);
                intent1.putExtra(Constant.MAX_NUMBER, 5);
                startActivityForResult(intent1, Constant.REQUEST_CODE_PICK_IMAGE);
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
            case R.id.bt_fabu:
                break;
        }
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

                    for (ImageFile file : list) {
                        String path = file.getPath();
                        imgList.add(path);
                    }
                    handler.sendEmptyMessage(IMAGECHANGED);
//                    gridViewPhoto.notifyDataSetChanged();
                }
                break;
            case Constant.REQUEST_CODE_PICK_VIDEO:
                if (resultCode == RESULT_OK) {
                    ArrayList<VideoFile> list = data.getParcelableArrayListExtra(Constant.RESULT_PICK_VIDEO);
//                    for (VideoFile file : list) {
//                        String path = file.getPath();
//                        listvideo.add(path);
//                        gridViewVideo.notifyDataSetChanged();
//                    }
                }
                break;
        }
    }
}
