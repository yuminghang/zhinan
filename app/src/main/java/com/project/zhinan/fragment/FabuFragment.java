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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.loader.GlideImageLoader;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.project.zhinan.R;
import com.project.zhinan.utils.ConstantValue;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ymh on 2016/3/11.
 */
public class FabuFragment extends Fragment {

    private static final int IMAGE_PICKER = 1;
    private static final int IMGPOSTED = 2;
    private static final int NETWORK_EORR = 3;
    private static final int POSTSUCCESS = 4;
    private View view;
    private ImageView iv;
    private ImageButton button;
    private ImagePicker imagePicker;
    private Button upload;
    private EditText et_biaoti, et_cuciao, et_zhengwen;
    private String biaoti, cuxiao, zhengwen;
    private boolean isFinished = false;
    private String imgurl;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private SharedPreferences cookie;
    private String my_cookie;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IMGPOSTED:
                    if (imgResponse.contains("success")) {
                        Toast.makeText(getContext(), "图片上传成功", Toast.LENGTH_SHORT).show();
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(imgResponse);
                            imgurl = jsonObject.getString("imgurl");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        isFinished = true;

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
                        Toast.makeText(getContext(), "发布成功", Toast.LENGTH_SHORT).show();

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
    private String imgResponse;
    private String success;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fabufragment_layout, container, false);
        iv = (ImageView) view.findViewById(R.id.iv);
        button = (ImageButton) view.findViewById(R.id.button);
        et_biaoti = (EditText) view.findViewById(R.id.et_biaoti);
        et_cuciao = (EditText) view.findViewById(R.id.et_cuciao);
        et_zhengwen = (EditText) view.findViewById(R.id.et_zhengwen);
        upload = (Button) view.findViewById(R.id.upload);
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
        cookie = getContext().getSharedPreferences("cookie", Context.MODE_PRIVATE);
        my_cookie = cookie.getString("my_cookie", null);
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ImageGridActivity.class);
                FabuFragment.this.startActivityForResult(intent, IMAGE_PICKER);
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                biaoti = et_biaoti.getText().toString();
                cuxiao = et_cuciao.getText().toString();
                zhengwen = et_zhengwen.getText().toString();
                boolean cancel = false;
                if (TextUtils.isEmpty(biaoti)) {
                    cancel = true;
                }
                if (TextUtils.isEmpty(cuxiao)) {
                    cancel = true;
                }

                if (!TextUtils.isEmpty(zhengwen)) {
                    cancel = true;
                }
                if (!isFinished) {
                    Toast.makeText(getActivity(), "图片还没有上传完！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!cancel) {
                    Toast.makeText(getActivity(), "广告信息不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    uploadInfo();
                }
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

            jsonObject.put("addesc", biaoti + "****" + cuxiao + "****" + zhengwen);
            jsonObject.put("adurl", "www.baidu.com");
            jsonObject.put("imgurl", imgurl);
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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMAGE_PICKER) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                imagePicker.getImageLoader().displayImage(getActivity(), images.get(0).path, iv, 200, 400);
                //上传文件
                uploadImage(images.get(0).path);
            } else {
                Toast.makeText(getActivity(), "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 图片上传
     * @param path
     */
    private void uploadImage(String path) {
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

}
