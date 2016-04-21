package com.project.zhinan.fragment;

import android.content.Intent;
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
            } else {
                Toast.makeText(getActivity(), "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
