package com.project.zhinan.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ymh on 2016/3/11.
 */
public class FabuFragment extends Fragment {

    private static final int IMAGE_PICKER = 1;
    private View view;
    private ImageView iv;
    private ImageButton button;
    private ImagePicker imagePicker;
    private Button upload;
    private EditText et_biaoti, et_cuciao, et_zhengwen;
    private String biaoti, cuxiao, zhengwen;
    private boolean isFinished=false;




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
                if (!isFinished){
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


    private void uploadInfo() {


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

    private void uploadImage(String path)  {


        File file = new File(path);

        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        SharedPreferences cookie = getContext().getSharedPreferences("cookie", Context.MODE_PRIVATE);
        String my_cookie = cookie.getString("my_cookie", null);
        RequestBody requestBody = new MultipartBuilder()
                .type(MultipartBuilder.FORM)
                .addPart(Headers.of(
                        "Content-Disposition",
                        "form-data; name= uploadimg; filename=" +
                                file.getName()), fileBody)
                .build();
        Request request = new Request.Builder()
                .url("http://123.206.84.242:2888/upload")
                .addHeader("Cookie",my_cookie)
                .post(requestBody)
                .build();


        OkHttpClient mOkHttpClient=new OkHttpClient();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback()
        {
            @Override
            public void onFailure(Request request, IOException e) {
                Toast.makeText(getContext(),"网络错误",Toast.LENGTH_SHORT).show();            }

            @Override
            public void onResponse(Response response) throws IOException {
                String string = response.body().string();
                System.out.println(string+"------------------");
                if (string.contains("success")){

                }
            }
            //...
        });


    }

}
