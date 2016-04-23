package com.project.zhinan.view;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.project.zhinan.R;
import com.project.zhinan.base.fragment.BaseFragment;
import com.project.zhinan.utils.DensityUtils;

/**
 * Created by ymh on 2016/4/23.
 */
public class MyFaxianPopupWindow {
    private Activity activity;
    private View myPopView;
    private PopupWindow popupwindow;
    private EditText et;
    private Button btn;

    public MyFaxianPopupWindow(Activity activity, View view) {
        this.activity = activity;
        init(view);
    }

    private void init(View view) {
        initpopWindow(view);
        initAnimation();
    }

    private void initpopWindow(View view) {
        myPopView = LayoutInflater.from(activity).inflate(R.layout.faxianpopupwindow_layout, null, false);
        et = (EditText) myPopView.findViewById(R.id.et);
        btn = (Button) myPopView.findViewById(R.id.btn);
        // 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
        popupwindow = new PopupWindow(myPopView, 1080, DensityUtils.dp2px(activity, 350), true);
        // 设置动画效果
        popupwindow.setAnimationStyle(R.style.AnimationFade);
        //设置在showAtLocation之前才能起作用
        popupwindow.setBackgroundDrawable(new BitmapDrawable());
//        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.back));
        popupwindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(et.getText().toString())) {
                    Toast.makeText(activity, "评论不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(activity, "评论成功！", Toast.LENGTH_SHORT).show();
                popupwindow.dismiss();
            }
        });
    }

    private void initAnimation() {
        // 设置背景颜色变暗
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.6f;
        activity.getWindow().setAttributes(lp);
        popupwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.alpha = 1f;
                activity.getWindow().setAttributes(lp);
            }
        });
    }


}
