package com.project.zhinan.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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
import com.squareup.okhttp.internal.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ymh on 2016/3/11.
 */
public class FabuFragment extends Fragment implements View.OnClickListener {


    private static final int IMAGE_PICKER = 1;
    private static final int IMGPOSTED = 2;
    private static final int NETWORK_EORR = 3;
    private static final int POSTSUCCESS = 4;
    private ArrayAdapter<CharSequence> adapterClass;
    private ArrayAdapter<CharSequence> adapterAge;
    private ArrayAdapter<CharSequence> adapterGender;
    private View view;
    private ImagePicker imagePicker;
    private String path;
    private ImageView iv;
    private EditText mBiaotiEditText;
    private Spinner mClassSpinner;
    private Spinner mAgeSpinner;
    private Spinner mGenderSpinner;
    private ImageButton mButtonImageButton;
    private ImageView mIvImageView;
    private Button mPreButton;
    private LinearLayout mEditLinearLayout;
    private ScrollView mEditScrollView;
    private TextView mAdInfoTextView;
    private ImageView mPreImageView;
    private TextView mClassTextView;
    private TextView mAgeTextView;
    private TextView mGenderTextView;
    private Button mReEditButton;
    private Button mFabuButton;
    private LinearLayout mPreviewLinearLayout;
    private String etAdINfo;
    private String imgResponse;
    private String success;
    private String imgurl;
    private boolean isFinished;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IMGPOSTED:
                    if (imgResponse.contains("success")) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(imgResponse);
                            imgurl = jsonObject.getString("imgurl");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        isFinished = true;
                        uploadInfo();


                    } else {
                        try {
                            JSONObject jsonObject = new JSONObject(imgResponse);
                            String error = jsonObject.getString("error");
                            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    break;
                case NETWORK_EORR:
                    Toast.makeText(getContext(), "网络错误", Toast.LENGTH_SHORT).show();
                    break;
                case POSTSUCCESS:
                    if (success.contains("success")) {
                        Toast.makeText(getContext(), "发布成功", Toast.LENGTH_LONG).show();
                        resetData();

                    } else {
                        try {
                            JSONObject jsonObject = new JSONObject(imgResponse);
                            String error = jsonObject.getString("error");
                            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 上传完重置数据
     */
    private void resetData() {
        mPreviewLinearLayout.setVisibility(View.GONE);
        mEditScrollView.setVisibility(View.VISIBLE);
        mPreImageView.setImageResource(R.mipmap.default_image);
        mIvImageView.setImageResource(R.mipmap.default_image);
        mAdInfoTextView.setText("");
        mBiaotiEditText.setText("");
        path=null;
        imgurl=null;
        mClassSpinner.setSelection(0);
        mGenderSpinner.setSelection(0);
        mAgeSpinner.setSelection(0);
        adapterAge.notifyDataSetChanged();
        adapterClass.notifyDataSetChanged();
        adapterGender.notifyDataSetChanged();
        isFinished = false;
    }

    private SharedPreferences cookie;
    private String my_cookie;
    private String textClass;
    private String textAge;
    private String textGender;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fabufragment_layout, container, false);

        initView();
        initSpinner();
        return view;

    }

    private void initView() {
        mBiaotiEditText = (EditText) view.findViewById(R.id.et_biaoti);
        mClassSpinner = (Spinner) view.findViewById(R.id.s_class);
        mAgeSpinner = (Spinner) view.findViewById(R.id.s_age);
        mGenderSpinner = (Spinner) view.findViewById(R.id.s_gender);
        mButtonImageButton = (ImageButton) view.findViewById(R.id.button);
        mIvImageView = (ImageView) view.findViewById(R.id.iv);
        mPreButton = (Button) view.findViewById(R.id.bt_pre);
        mEditLinearLayout = (LinearLayout) view.findViewById(R.id.ll_edit);
        mEditScrollView = (ScrollView) view.findViewById(R.id.sv_edit);
        mAdInfoTextView = (TextView) view.findViewById(R.id.tv_adInfo);
        mPreImageView = (ImageView) view.findViewById(R.id.iv_pre);
        mClassTextView = (TextView) view.findViewById(R.id.tv_class);
        mAgeTextView = (TextView) view.findViewById(R.id.tv_age);
        mGenderTextView = (TextView) view.findViewById(R.id.tv_gender);
        mReEditButton = (Button) view.findViewById(R.id.bt_re_edit);
        mFabuButton = (Button) view.findViewById(R.id.bt_fabu);
        mPreviewLinearLayout = (LinearLayout) view.findViewById(R.id.ll_preview);
        imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(9);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
        mReEditButton.setOnClickListener(this);
        mPreButton.setOnClickListener(this);
        mButtonImageButton.setOnClickListener(this);
        mFabuButton.setOnClickListener(this);

    }

    private void initSpinner() {
        adapterClass = ArrayAdapter.createFromResource(getContext(), R.array.tag_class, R.layout.support_simple_spinner_dropdown_item);
        adapterAge = ArrayAdapter.createFromResource(getContext(), R.array.tag_age, R.layout.support_simple_spinner_dropdown_item);
        adapterGender = ArrayAdapter.createFromResource(getContext(), R.array.tag_gender, R.layout.support_simple_spinner_dropdown_item);
        mAgeSpinner.setAdapter(adapterAge);
        mClassSpinner.setAdapter(adapterClass);
        mGenderSpinner.setAdapter(adapterGender);
    }

    @Override
    public void onResume() {
        super.onResume();
        cookie = getContext().getSharedPreferences("cookie", Context.MODE_PRIVATE);
        my_cookie = cookie.getString("my_cookie", null);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMAGE_PICKER) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                imagePicker.getImageLoader().displayImage(getActivity(), images.get(0).path, mIvImageView, 200, 400);
                //上传文件
                path = images.get(0).path;

            } else {
                Toast.makeText(getActivity(), "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_re_edit:
                //重新编辑
                reEdit();
                break;
            case R.id.bt_pre:
                //预览
                preView();
                break;
            case R.id.bt_fabu:

                SharedPreferences loginSp = getContext().getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
                boolean isLogin = loginSp.getBoolean("isLogin", false);
                if (!isLogin){
                    Toast.makeText(getContext(),"没有登陆！",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(), LoginActivity.class));
                    return;
                }
                uploadImage(path);
                break;
            case R.id.button:
                Intent intent = new Intent(getActivity(), ImageGridActivity.class);
                FabuFragment.this.startActivityForResult(intent, IMAGE_PICKER);
                break;
            default:
                break;
        }

    }

    private void reEdit() {
        mPreviewLinearLayout.setVisibility(View.GONE);
        mEditScrollView.setVisibility(View.VISIBLE);
    }

    /***
     * 预览
     */
    private void preView() {
        etAdINfo = mBiaotiEditText.getText().toString();
        if (TextUtils.isEmpty(etAdINfo)) {
            Toast.makeText(getActivity(), "广告信息不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(path)) {
            Toast.makeText(getActivity(), "没有选择广告图片！", Toast.LENGTH_SHORT).show();
            return;
        }
        textClass = mClassSpinner.getSelectedItem().toString();
        mClassTextView.setText("类型：" + textClass);
        textAge = mAgeSpinner.getSelectedItem().toString();
        mAgeTextView.setText("年龄："+ textAge);
        textGender = mGenderSpinner.getSelectedItem().toString();
        mGenderTextView.setText("性别："+ textGender);
        mAdInfoTextView.setText(etAdINfo);
        File file = new File(path);
        Glide.with(getContext()).load(file).placeholder(R.mipmap.default_image).into(mPreImageView);
        mPreviewLinearLayout.setVisibility(View.VISIBLE);
        mEditScrollView.setVisibility(View.GONE);

    }

    /**
     * 图片上传
     * @param path
     */
    private synchronized void uploadImage(String path) {
        isFinished = false;

        //多个图片文件列表
        List<File> list = new ArrayList<File>();
        File file = new File(path);
        list.add(file);
        //多文件表单上传构造器
        MultipartBuilder multipartBuilder = new MultipartBuilder().type(MultipartBuilder.FORM);
        //添加一个文本表单参数
        multipartBuilder.addFormDataPart("name", "uploadimg");
        for (File file1 : list) {
            if (file1.exists()) {
                multipartBuilder.addFormDataPart("filename", file1.getName(), RequestBody.create(MediaType.parse("image/png"), file1));
            }
        }
        //构造文件上传时的请求对象Request
        Request request = new Request.Builder().addHeader("Cookie", my_cookie).url(ConstantValue.UploadUrl).post(multipartBuilder.build()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                handler.sendEmptyMessage(NETWORK_EORR);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                imgResponse = response.body().string();
                handler.sendEmptyMessage(IMGPOSTED);
            }
        });


    }
    /**
     * 发布新的广告
     */
    private void uploadInfo() {
        //申明给服务端传递一个json串
        //创建一个OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //创建一个RequestBody(参数1：数据类型 参数2传递的json串)

        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("addesc", etAdINfo);
            jsonObject.put("adurl", "www.baidu.com");
            jsonObject.put("imgurl", imgurl);
            jsonObject.put("tag1",textClass);
            jsonObject.put("tag2",textAge);
            jsonObject.put("tag3",textGender);
            jsonObject.put("icons", 2);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String json = jsonObject.toString();
        RequestBody requestBody = RequestBody.create(JSON, json);
        //创建一个请求对象
        Request request = new Request.Builder()
                .url(ConstantValue.PostUrl)
                .addHeader("Cookie", my_cookie)
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                handler.sendEmptyMessage(NETWORK_EORR);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                success = response.body().string();
                handler.sendEmptyMessage(POSTSUCCESS);
            }
        });
//

    }
}
